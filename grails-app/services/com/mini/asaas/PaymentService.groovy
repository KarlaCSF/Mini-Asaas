package com.mini.asaas

import com.mini.asaas.Payment
import com.mini.asaas.Customer
import com.mini.asaas.dto.PaymentDTO
import grails.gorm.transactions.Transactional

@Transactional
class PaymentService {
    public Payment save(PaymentDTO paymentDTO, Long customerId) {
        Payment payment = new Payment()
        
        payment.customer = Customer.get(customerId)
        payment.payer = Payer.get(paymentDTO.payerId)
        payment.value = paymentDTO.value
        payment.dueDate = paymentDTO.dueDate
        payment.paymentType = paymentDTO.paymentType
        
        return payment.save(failOnError: true)
    }
}
