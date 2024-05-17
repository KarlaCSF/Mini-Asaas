package com.mini.asaas

import com.mini.asaas.Address
import grails.gorm.transactions.Transactional
import com.mini.asaas.dto.AddressDto

@Transactional
class AddressService {
   public Address save(AddressDto addressDto) {
        Address address = new Address() 
        address.cep = addressDto.cep
        address.city = addressDto.city
        address.state = addressDto.state
        address.district = addressDto.district
        address.street = addressDto.street
        address.number = addressDto.number
        address.complement = addressDto.complement
       return address.save(failOnError: true)
   }
}
