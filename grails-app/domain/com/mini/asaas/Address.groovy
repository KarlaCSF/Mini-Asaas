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
      cep blank: false, maxSize: 8
      city blank: false
      state blank: false, maxSize: 2
      district blank: false
      street blank: false
      number blank: false
      complement blank: true
   }

   static mapping = {
      tablePerHierarchy false
   }
}
