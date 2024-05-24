package com.mini.asaas.payment

import com.mini.asaas.payment.Payment
import com.mini.asaas.Customer
import com.mini.asaas.Payer
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.enums.payment.PaymentStatus
import grails.gorm.transactions.Transactional

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
        Payment payment = Payment.where{
            id == paymentId 
            && customer.id == customerId 
            && deleted == false
        }.first()
        
        payment.lastUpdated = new Date()
        payment.value = updatePaymentDTO.value
        payment.dueDate = updatePaymentDTO.dueDate
        payment.billingType = updatePaymentDTO.billingType
 
        return payment.save(failOnError: true)
    }

    public Payment show(Long paymentId, Long customerId) {
        Payment payment = Payment.where{
            id == paymentId 
            && customer.id == customerId 
            && deleted == false
        }.first()
        return payment
    }
    
    public void delete(Long paymentId, Long customerId) {
        Payment payment = Payment.where{
            id == paymentId 
            && customer.id == customerId 
            && deleted == false
        }.first()

        payment.deleted = true 
        payment.save(failOnError: true)
    }
}
