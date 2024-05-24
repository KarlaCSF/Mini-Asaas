package com.mini.asaas.dto.payment

import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.utils.ParseUtil
import java.math.BigDecimal
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CreatePaymentDTO {
  
    Long payerId

    BigDecimal value

    Date dueDate

    BillingType billingType

    PaymentStatus status

    CreatePaymentDTO(Map params) {
        this.payerId = params.payerId as Long
        this.value = new BigDecimal(params.value.toString())
        this.dueDate = ParseUtil.date(params.dueDate.toString())
        this.billingType = params.billingType as BillingType
        this.status = PaymentStatus.WAITING
    } 
}
