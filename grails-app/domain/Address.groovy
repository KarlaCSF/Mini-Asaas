package com.mini.asaas

import com.mini.asaas.utils.BaseEntity

class Address extends BaseEntity {
   String cep

   String city

   String state

   String district

   String street

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
