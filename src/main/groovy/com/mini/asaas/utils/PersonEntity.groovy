package com.mini.asaas.utils

import com.mini.asaas.Address
import com.mini.asaas.enums.PersonType
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
abstract class PersonEntity extends BaseEntity {

    String name
    
    String email
    
    PersonType personType
    
    String cpfCnpj

    Address address

    static constraints = {
      name  blank: false
      email blank: false, email: true 
      personType blank: false
      cpfCnpj blank: false, minSize: 11, maxSize: 14
    }
}
