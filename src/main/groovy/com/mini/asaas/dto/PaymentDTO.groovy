package com.mini.asaas.dto

import com.mini.asaas.enums.PaymentType
import java.math.BigDecimal
import java.text.SimpleDateFormat

class PaymentDTO {
  
    Long payerId

    BigDecimal value

    Date dueDate

    PaymentType paymentType

    PaymentDTO(Map params) {
        this.payerId = params.payerId as Long
        this.value = new BigDecimal(params.value)
        this.dueDate = parseDate(params.dueDate)
        this.paymentType = PaymentType.valueOf(params.paymentType)
    } 
    
    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
        dateFormat.setLenient(false)
        return dateFormat.parse(dateString)
    }
}
