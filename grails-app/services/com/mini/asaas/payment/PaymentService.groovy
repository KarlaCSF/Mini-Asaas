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
    public Payment save(CreatePaymentDTO createPaymentDTO, Customer customer) {
        Payment payment = new Payment()
        
        payment.customer = customer
        payment.payer = Payer.get(createPaymentDTO.payerId)
        payment.value = createPaymentDTO.value
        payment.dueDate = createPaymentDTO.dueDate
        payment.billingType = createPaymentDTO.billingType
        payment.paymentStatus = createPaymentDTO.paymentStatus
        
        return payment.save(failOnError: true)
    }

    public Payment update(UpdatePaymentDTO updatePaymentDTO, Payment payment ) {
        payment.lastUpdated = new Date()
        payment.value = updatePaymentDTO.value
        payment.dueDate = updatePaymentDTO.dueDate
        payment.billingType = updatePaymentDTO.billingType

        return payment.save(failOnError: true)
    }

    public void delete(Payment payment) {
        payment.softDelete()        
    }
}
