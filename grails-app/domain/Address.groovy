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
      cep maxSize: 8
      state maxSize: 2
   }

   static mapping = {
      tablePerHierarchy false
   }
}
