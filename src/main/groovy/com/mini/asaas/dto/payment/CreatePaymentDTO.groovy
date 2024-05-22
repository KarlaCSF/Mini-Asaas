package com.mini.asaas.dto.payment

import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.enums.payment.PaymentStatus
import java.math.BigDecimal
import java.text.SimpleDateFormat

class CreatePaymentDTO {
  
    Long payerId

    BigDecimal value

    Date dueDate

    BillingType billingType

    PaymentStatus paymentStatus

    CreatePaymentDTO(Map params) {
        this.payerId = params.payerId as Long
        this.value = new BigDecimal(params.value)
        this.dueDate = parseDate(params.dueDate)
        this.billingType = BillingType.valueOf(params.billingType)
        this.paymentStatus = PaymentStatus.WAITING
    } 
    
    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
        dateFormat.setLenient(false)
        return dateFormat.parse(dateString)
    }
}
