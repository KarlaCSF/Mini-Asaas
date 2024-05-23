package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale

enum BillingType {
    
    BANK_SLIP,
    CREDIT,
    DEBIT,
    MONEY,
    PIX
    
    String getMessage() {
        def messageSource = Holders.applicationContext.getBean("messageSource")
        messageSource.getMessage("BillingType.${this.name()}", null, "", new Locale("pt", "BR"))
    }
}