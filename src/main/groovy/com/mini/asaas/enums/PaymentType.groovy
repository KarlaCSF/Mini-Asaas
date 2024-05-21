package com.mini.asaas.enums

import grails.util.Holders
import java.util.Locale

enum PaymentType {
    
    CREDIT,
    DEBIT,
    PIX,
    MONEY
    
   public static PaymentType convert(String paymentType) {
       try {
           paymentType = paymentType.toUpperCase()
           return paymentType as PaymentType
       } catch (Exception ignored) {
           return null
       }
   }

   String getLabel() {
        Holders.applicationContext.getBean("messageSource").getMessage("PaymentType.${this}.label", null, "", new Locale("pt", "BR"))
    }
}