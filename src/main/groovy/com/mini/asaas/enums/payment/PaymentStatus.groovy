package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale

enum PaymentStatus {
    OVERDUE,
    PAYED,
    WAITING
     
    String getMessage() {
        def messageSource = Holders.applicationContext.getBean("messageSource")
        messageSource.getMessage("PaymentStatus.${this.name()}", null, "", new Locale("pt", "BR"))
    }
}