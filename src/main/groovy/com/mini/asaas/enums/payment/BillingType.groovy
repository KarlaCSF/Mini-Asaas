package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale
import org.springframework.web.context.request.RequestContextHolder
import grails.compiler.GrailsCompileStatic
import org.springframework.context.MessageSource
import groovy.transform.CompileDynamic

@GrailsCompileStatic
enum BillingType {
    BANK_SLIP,
    CREDIT,
    DEBIT,
    MONEY,
    PIX
    
    @CompileDynamic
    String getMessage() {
        Locale locale = RequestContextHolder.currentRequestAttributes().getLocale()
        MessageSource messageSource = Holders.applicationContext.getBean("messageSource")
        return messageSource.getMessage("BillingType.${this.name()}", null, "", locale)
    }
}
