package com.mini.asaas

import com.mini.asaas.PersonType

abstract class Person {
    Date dateCreated
    Date lastUpdated
    Boolean deleted = false
    String name
    String email
    PersonType personType
    String cpfCnpj
    String endereco

    static constraints = {
      name nullable: false
      email nullable: false, email: true
      personType nullable: true
      cpfCnpj nullable: false
      endereco nullable: true
    }

    static mapping = {
        tablePerHierarchy false
    }
}