package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import com.mini.asaas.dto.CustomerDTO
import com.mini.asaas.enums.PersonType

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@Transactional
class CustomerService {

    AddressService addressService

    public Customer save(CustomerDto customerDto) {
        Customer customerValues = validateCustomer(customerDto)

        if (customerValues.hasErrors()) {
            throw new ValidationException("Erro ao salvar conta", customerValues.errors)
        }
        
        Customer customer = new Customer()
        customer.name = customerDto.name
        customer.email = customerDto.email
        customer.cpfCnpj = customerDto.cpfCnpj
        customer.personType = customer.cpfCnpj.size() > 11 ? PersonType.LEGAL : PersonType.NATURAL
        
        customer.address = addressService.save(customerDTO.addressDTO)
        
        return customer.save(failOnError: true)
   }

    private Customer validateCustomer(CustomerDto customerDto) {
        Customer customer = new Customer()
        
        if (!Validators.validateByType(customerDto.cpfCnpj)) {
            customer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return customer
    }
}
