package com.mini.asaas

import com.mini.asaas.Address
import com.mini.asaas.dto.AddressDTO

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
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

    public Address update(AddressDTO addressDTO, Long addressId) {
        Address address = Address.where{
            id == addressId 
            && deleted == false
        }.first()
        
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
