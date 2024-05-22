package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale

enum BillingType {
    
    CREDIT,
    DEBIT,
    PIX,
    MONEY
    
    static List<Map<String, String>> listBillingTypes() {
        def messageSource = Holders.applicationContext.getBean("messageSource")
        List<Map<String, String>> listBillingType = []
        
        BillingType.values().each { type ->
            String label = messageSource.getMessage("BillingType.${type.name()}", null, "", new Locale("pt", "BR"))
            listBillingType << [id: type, name: label]
        }
        return listBillingType
    }
    
    String getMessage() {
        def messageSource = Holders.applicationContext.getBean("messageSource")
        messageSource.getMessage("BillingType.${this.name()}", null, "", new Locale("pt", "BR"))
    }
}