package com.mini.asaas.utils

import com.mini.asaas.PersonType

abstract class PersonEntity extends BaseEntity {
    String name
    String email
    PersonType personType
    String cpfCnpj
    String endereco

    static constraints = {
      name nullable: false
      email nullable: false, email: true
      personType nullable: true
      cpfCnpj nullable: false, minSize: 11, maxSize: 14, unique: true
      endereco nullable: true
    }

    static mapping = {
        tablePerHierarchy false
    }
}
