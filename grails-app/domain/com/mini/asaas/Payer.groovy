package com.mini.asaas

import com.mini.asaas.utils.PersonEntity
import com.mini.asaas.Customer
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Payer extends PersonEntity {
    Customer customer
}
