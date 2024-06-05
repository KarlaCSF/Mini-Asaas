package com.mini.asaas.customer

import com.mini.asaas.adress.Address
import com.mini.asaas.adress.AddressService
import com.mini.asaas.customer.Customer
import com.mini.asaas.dto.customer.CustomerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class CustomerService {

    AddressService addressService

    public Customer save(CustomerDTO customerDTO) {
        Customer validatedCustomer = validateSave(customerDTO, new Customer())

        if (validatedCustomer.hasErrors()) throw new ValidationException("Erro ao salvar conta", validatedCustomer.errors)
 
        validatedCustomer.name = customerDTO.name
        validatedCustomer.email = customerDTO.email
        validatedCustomer.cpfCnpj = customerDTO.cpfCnpj
        validatedCustomer.personType = CpfCnpjUtils.getPersonType(validatedCustomer.cpfCnpj)
        
        validatedCustomer.address = addressService.save(customerDTO.addressDTO)
        
        return validatedCustomer.save(failOnError: true)
   }

    public Customer update(CustomerDTO customerDTO, Long customerId) {
        Customer customer = Customer.where{
            id == customerId 
            && deleted == false
        }.first()

        Customer validatedCustomer = validateSave(customerDTO, customer)

        if (validatedCustomer.hasErrors()) throw new ValidationException("Erro ao editar conta", validatedCustomer.errors)
        
        validatedCustomer.name = customerDTO.name
        validatedCustomer.email = customerDTO.email
        validatedCustomer.cpfCnpj = customerDTO.cpfCnpj
        validatedCustomer.personType = CpfCnpjUtils.getPersonType(validatedCustomer.cpfCnpj)
        validatedCustomer.address = addressService.update(customerDTO.addressDTO, validatedCustomer.address.id)
        
        return validatedCustomer.save(failOnError: true)
    }

    private Customer validateSave(CustomerDTO customerDTO, Customer customer) {       
        if (!CpfCnpjUtils.validate(customerDTO.cpfCnpj)) {
            customer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return customer
    }
}
