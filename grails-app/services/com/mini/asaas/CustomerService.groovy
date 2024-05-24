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

    public Customer save(CustomerDTO customerDTO) {
        Customer customerValues = validateSave(customerDTO)

        if (customerValues.hasErrors()) {
            throw new ValidationException("Erro ao salvar conta", customerValues.errors)
        }
        
        Customer customer = new Customer()
        customer.name = customerDTO.name
        customer.email = customerDTO.email
        customer.cpfCnpj = customerDTO.cpfCnpj
        customer.personType = customer.cpfCnpj.size() > 11 ? PersonType.LEGAL : PersonType.NATURAL
        
        customer.address = addressService.save(customerDTO.addressDTO)
        
        return customer.save(failOnError: true)
   }

    private Customer validateSave(CustomerDTO customerDTO) {
        Customer customer = new Customer()
        
        if (!CpfCnpjUtils.validate(customerDTO.cpfCnpj)) {
            customer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return customer
    }
}
