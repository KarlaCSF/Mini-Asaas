package com.mini.asaas.dto.payment

import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.utils.StringUtils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UpdatePaymentDTO {
  
    BigDecimal value

    Date dueDate

    BillingType billingType

    UpdatePaymentDTO(Map params) {
        this.value = StringUtils.parseStringToBigDecimal(params.value.toString())
        this.dueDate = StringUtils.parseStringToDate(params.dueDate.toString())
        this.billingType = params.billingType as BillingType
    }
}
