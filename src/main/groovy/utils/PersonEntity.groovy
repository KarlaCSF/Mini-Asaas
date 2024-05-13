package com.mini.asaas.utils
import com.mini.asaas.utils.BaseEntity
import com.mini.asaas.PersonType   

abstract class PersonEntity extends BaseEntity {
    String name
    String email
    PersonType personType
    String cpfCnpj
    String adress
    String password

    static constraints = {
        name nullable: false
        email nullable: false, email: true
        personType nullable: true
        cpfCnpj nullable: false, minSize: 11, maxSize: 14, unique: true
        adress nullable: true
        password nullable:false
    }

    static mapping = {
        tablePerHierarchy false
    }
}