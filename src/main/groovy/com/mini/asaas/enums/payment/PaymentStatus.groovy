package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale
import org.springframework.web.context.request.RequestContextHolder

enum PaymentStatus {
    OVERDUE,
    PAYED,
    WAITING
    
    String getMessage() {
        Locale locale = RequestContextHolder.currentRequestAttributes().getLocale()
        def messageSource = Holders.applicationContext.getBean("messageSource")
        messageSource.getMessage("PaymentStatus.${this.name()}", null, "", locale)
    }
}