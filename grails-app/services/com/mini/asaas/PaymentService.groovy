package com.mini.asaas

import com.mini.asaas.Payment
import com.mini.asaas.Customer
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.enums.payment.PaymentStatus

import grails.gorm.transactions.Transactional

@Transactional
class PaymentService {
    public Payment save(CreatePaymentDTO createPaymentDTO, Long customerId) {
        Payment payment = new Payment()
        
        payment.customer = Customer.get(customerId)
        payment.payer = Payer.get(createPaymentDTO.payerId)
        payment.value = createPaymentDTO.value
        payment.dueDate = createPaymentDTO.dueDate
        payment.billingType = createPaymentDTO.billingType
        payment.paymentStatus = createPaymentDTO.paymentStatus
        
        return payment.save(failOnError: true)
    }

    public Payment update(UpdatePaymentDTO updatePaymentDTO, PaymentStatus paymentStatus ) {
        Payment payment = new Payment()

        payment.lastUpdated = new Date()        
        payment.value = createPaymentDTO.value
        payment.dueDate = createPaymentDTO.dueDate
        payment.billingType = createPaymentDTO.billingType
        payment.paymentStatus = paymentStatus

        return payment.save(failOnError: true)
    }
}
