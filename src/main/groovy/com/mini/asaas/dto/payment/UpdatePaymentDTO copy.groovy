package com.mini.asaas.dto.payment

import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.utils.ParseUtil
import java.math.BigDecimal

class UpdatePaymentDTO {
  
    BigDecimal value

    Date dueDate

    BillingType billingType

    UpdatePaymentDTO(Map params) {
        this.value = new BigDecimal(params.value)
        this.dueDate = ParseUtil.date(params.dueDate)
        this.billingType = BillingType.valueOf(params.billingType)
    }  
}