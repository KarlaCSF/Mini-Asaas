package com.mini.asaas.payer

import com.mini.asaas.base.PersonEntity
import com.mini.asaas.customer.Customer
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Payer extends PersonEntity {
    Customer customer
}
