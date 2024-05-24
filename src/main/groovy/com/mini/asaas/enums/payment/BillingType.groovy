package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale
import org.springframework.web.context.request.RequestContextHolder

enum BillingType {
    BANK_SLIP,
    CREDIT,
    DEBIT,
    MONEY,
    PIX
    
    String getMessage() {
        Locale locale = RequestContextHolder.currentRequestAttributes().getLocale()
        def messageSource = Holders.applicationContext.getBean("messageSource")
        messageSource.getMessage("BillingType.${this.name()}", null, "", locale)
    }
}