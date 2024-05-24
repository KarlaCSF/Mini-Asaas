package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic
import grails.gorm.dirty.checking.DirtyCheck

@DirtyCheck
@GrailsCompileStatic
abstract class BaseEntity {

    Date dateCreated

    Date lastUpdated
    
    Boolean deleted = false
  
}