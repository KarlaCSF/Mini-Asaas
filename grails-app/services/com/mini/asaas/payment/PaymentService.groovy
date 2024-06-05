package com.mini.asaas.payment

import com.mini.asaas.Customer
import com.mini.asaas.email.EmailService
import com.mini.asaas.Payer
import com.mini.asaas.payment.Payment
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.repositories.PaymentRepository
import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class PaymentService {

    EmailService emailService

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
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new Exception("Essa cobrança não pode ser modificada")
        payment.value = updatePaymentDTO.value
        payment.dueDate = updatePaymentDTO.dueDate
        payment.billingType = updatePaymentDTO.billingType
        return payment.save(failOnError: true)
    }

    public Payment findByIdAndCustomerId(Long paymentId, Long customerId) {
        return PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
    }
    
    public void delete(Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new Exception("Essa cobrança não pode ser modificada")
        payment.deleted = true 
        payment.save(failOnError: true)
    }

    public List<Payment> listByCustomer(Long customerId){
        return PaymentRepository.listByCustomer(customerId)
    }
    
    public List<Payment> listByStatus(PaymentStatus status){
        return PaymentRepository.listByStatus(status)
    }

    public void notifyWaitingPayments() {
        List<Payment> paymentList = listByStatus(PaymentStatus.WAITING)

        paymentList.each { payment ->
            emailService.sendEmailToVerifyPayment(payment)
        }
    }
}
