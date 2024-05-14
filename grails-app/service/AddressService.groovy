package com.mini.asaas

import com.mini.asaas.Address
import grails.gorm.transactions.Transactional

@Transactional
class AddressService {
   public Address save(String cep, String city, String state, String district, String street, String number, String complement) {
       Address address = new Address()
       address.cep = cep
       address.city = city
       address.state = state
       address.district = district
       address.street = street
       address.number = number
       address.complement = complement
       
       return address.save(failOnError: true)
   }
}
