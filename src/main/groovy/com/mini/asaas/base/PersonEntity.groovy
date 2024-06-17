package com.mini.asaas.base

import com.mini.asaas.address.Address
import com.mini.asaas.enums.PersonType
import grails.compiler.GrailsCompileStatic
import grails.gorm.dirty.checking.DirtyCheck

@DirtyCheck
@GrailsCompileStatic
abstract class PersonEntity extends BaseEntity {

    String name

    String email

    PersonType personType

    String cpfCnpj

    Address address

    String phone

    static constraints = {
        name blank: false
        email blank: false, email: true
        personType blank: false
        cpfCnpj blank: false, minSize: 11, maxSize: 14
        phone blank: false, minSize: 10, maxSize: 11
    }
}
