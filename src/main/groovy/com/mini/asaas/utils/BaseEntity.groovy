package com.mini.asaas.utils
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
abstract class BaseEntity {

    Date dateCreated

    Date lastUpdated
    
    Boolean deleted = false
}