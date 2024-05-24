package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import com.mini.asaas.dto.CustomerDTO
import com.mini.asaas.enums.PersonType

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class CustomerService {

    AddressService addressService

    public Customer save(CustomerDTO customerDTO) {
        Customer customer = new Customer()
        
        customer.name = customerDTO.name
        customer.email = customerDTO.email
        customer.cpfCnpj = customerDTO.cpfCnpj
        customer.personType = PersonType.NATURAL
        
        customer.address = addressService.save(customerDTO.addressDTO)
        
        return customer.save(failOnError: true)
   }
}
