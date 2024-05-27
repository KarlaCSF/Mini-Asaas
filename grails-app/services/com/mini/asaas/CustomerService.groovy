package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import com.mini.asaas.dto.CustomerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class CustomerService {

    AddressService addressService

    public Customer save(CustomerDTO customerDTO) {
        Customer customer = validateSave(customerDTO)

        if (customer.hasErrors()) throw new ValidationException("Erro ao salvar conta", customer.errors)
 
        customer.name = customerDTO.name
        customer.email = customerDTO.email
        customer.cpfCnpj = customerDTO.cpfCnpj
        customer.personType = CpfCnpjUtils.getPersonType(customer.cpfCnpj)
        
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
