package com.mini.asaas.dto.payment

import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.utils.ParseUtil
import java.math.BigDecimal
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UpdatePaymentDTO {
  
    BigDecimal value

    Date dueDate

    BillingType billingType

    UpdatePaymentDTO(Map params) {
        this.value = new BigDecimal(params.value.toString())
        this.dueDate = ParseUtil.date(params.dueDate.toString())
        this.billingType = params.billingType as BillingType
    }  
}
