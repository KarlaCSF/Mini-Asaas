package com.mini.asaas.payer

import com.mini.asaas.base.PersonEntity
import com.mini.asaas.Customer
import grails.compiler.GrailsCompileStatic
import grails.gorm.dirty.checking.DirtyCheck

@DirtyCheck
@GrailsCompileStatic
class Payer extends PersonEntity {
    Customer customer
}
