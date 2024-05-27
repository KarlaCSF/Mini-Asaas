package com.mini.asaas.enums.payment

import grails.util.Holders
import java.util.Locale
import org.springframework.web.context.request.RequestContextHolder
import grails.compiler.GrailsCompileStatic
import org.springframework.context.MessageSource
import groovy.transform.CompileDynamic

@GrailsCompileStatic
enum PaymentStatus {
    OVERDUE,
    PAYED,
    WAITING
    
    @CompileDynamic
    String getMessage() {
        Locale locale = RequestContextHolder.currentRequestAttributes().getLocale()
        MessageSource messageSource = Holders.applicationContext.getBean("messageSource")
        return messageSource.getMessage("PaymentStatus.${this.name()}", null, "", locale)
    }
}
