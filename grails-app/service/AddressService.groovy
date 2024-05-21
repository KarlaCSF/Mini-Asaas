package com.mini.asaas

import com.mini.asaas.Address
import grails.gorm.transactions.Transactional
import com.mini.asaas.dto.AddressDTO

@Transactional
class AddressService {
   public Address save(AddressDTO addressDTO) {
        Address address = new Address() 
        address.cep = addressDTO.cep
        address.city = addressDTO.city
        address.state = addressDTO.state
        address.district = addressDTO.district
        address.street = addressDTO.street
        address.number = addressDTO.number
        address.complement = addressDTO.complement
       return address.save(failOnError: true)
   }
}
