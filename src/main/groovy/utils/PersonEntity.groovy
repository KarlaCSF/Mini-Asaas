package com.mini.asaas.utils

import com.mini.asaas.PersonType

abstract class PersonEntity extends BaseEntity {

    String name
    
    String email
    
    PersonType personType
    
    String cpfCnpj

    static constraints = {
      email email: true
      cpfCnpj minSize: 11, maxSize: 14
    }

    static mapping = {
        tablePerHierarchy false
    }
}
