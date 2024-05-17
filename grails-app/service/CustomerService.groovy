package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import grails.gorm.transactions.Transactional
import com.mini.asaas.dto.CustomerDto

@Transactional
class CustomerService {

    AddressService addressService

    public Customer save(CustomerDto customerDto) {
        Customer customer = new Customer()
        
        customer.name = customerDto.name
        customer.email = customerDto.email
        customer.cpfCnpj = customerDto.cpfCnpj
        customer.personType = PersonType.NATURAL
        
        customer.address = addressService.save(customerDto.addressDto)
        
        return customer.save(failOnError: true)
   }
}
