package com.mini.asaas.dto.payment

import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.utils.ParseUtil
import java.math.BigDecimal

class CreatePaymentDTO {
  
    Long payerId

    BigDecimal value

    Date dueDate

    BillingType billingType

    PaymentStatus status

    CreatePaymentDTO(Map params) {
        this.payerId = params.payerId as Long
        this.value = new BigDecimal(params.value)
        this.dueDate = ParseUtil.date(params.dueDate)
        this.billingType = BillingType.valueOf(params.billingType)
        this.status = PaymentStatus.WAITING
    } 
}
