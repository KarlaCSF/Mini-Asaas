package com.mini.asaas.payment

import com.mini.asaas.payment.Payment
import com.mini.asaas.Customer
import com.mini.asaas.payer.Payer
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.repositories.PaymentRepository
import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class PaymentService {
    public Payment save(CreatePaymentDTO createPaymentDTO, Long customerId) {
        Payment payment = new Payment()
        
        payment.customer = Customer.where{
            id == customerId
            && deleted == false
        }.first()
        
        payment.payer = Payer.where{
            id == createPaymentDTO.payerId
            && customer.id == customerId
            && deleted == false
        }.first()
        
        payment.value = createPaymentDTO.value
        payment.dueDate = createPaymentDTO.dueDate
        payment.billingType = createPaymentDTO.billingType
        payment.status = createPaymentDTO.status
        
        return payment.save(failOnError: true)
    }

    public Payment update(UpdatePaymentDTO updatePaymentDTO, Long paymentId, Long customerId ) {
        Payment payment = findByIdAndCustomerId(paymentId, customerId, false)
        if (!payment.canEdit()) throw new Exception("Essa cobrança não pode ser modificada")
        payment.value = updatePaymentDTO.value
        payment.dueDate = updatePaymentDTO.dueDate
        payment.billingType = updatePaymentDTO.billingType
        return payment.save(failOnError: true)
    }

    public void delete(Long paymentId, Long customerId) {
        Payment payment = findByIdAndCustomerId(paymentId, customerId, false)
        if (!payment.canEdit()) throw new Exception("Essa cobrança não pode ser modificada")
        payment.deleted = true 
        payment.save(failOnError: true)
    }

    public Payment restore(Long paymentId, Long customerId) {
        Payment payment = findByIdAndCustomerId(paymentId, customerId, true)
        payment.deleted = false
        payment.save(failOnError: true)   
    }

    public Payment findByIdAndCustomerId(Long paymentId, Long customerId, Boolean deleted) {
        return PaymentRepository.findByIdAndCustomerId(paymentId, customerId, deleted)
    }

    public List<Payment> listByCustomer(Long customerId, Boolean deleted){
        return PaymentRepository.listByCustomer(customerId, deleted)
    }
}
