package com.mini.asaas.utils

import com.mini.asaas.PersonType

abstract class PersonEntity extends BaseEntity {
    String name
    String email
    PersonType personType
    String cpfCnpj
    Address address

    static constraints = {
      name nullable: false
      email nullable: false, email: true
      personType nullable: true
      cpfCnpj nullable: false, minSize: 11, maxSize: 14
      address nullable: true
    }

    static mapping = {
        tablePerHierarchy false
    }
}
