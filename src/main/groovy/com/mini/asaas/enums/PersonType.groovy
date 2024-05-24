package com.mini.asaas.enums

import grails.util.Holders
import java.util.Locale
import grails.compiler.GrailsCompileStatic
import groovy.transform.CompileDynamic

@GrailsCompileStatic
enum PersonType {
    
    NATURAL,
    LEGAL

   public static PersonType convert(String personType) {
       try {
           personType = personType.toUpperCase()
           return personType as PersonType
       } catch (Exception ignored) {
           return null
       }
   }

    @CompileDynamic
    public String getLabel() {
        return Holders.applicationContext.getBean("messageSource").getMessage("PersonType.${this}.label", null, "", new Locale("pt", "BR"))
    }
}
