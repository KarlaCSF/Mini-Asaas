package com.mini.asaas.utils

class Address extends BaseEntity {
   String cep
   String district
   String city
   String state
   String number
   String complement

   static constraints = {
      cep nullable: false, maxSize: 8
      district nullable: false
      city nullable: false
      state nullable: false, maxSize: 2
      number nullable: false
      complement nullable: true
   }

   static mapping = {
      tablePerHierarchy false
   }
}
