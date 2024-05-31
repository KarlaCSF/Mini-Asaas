package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import com.mini.asaas.dto.customer.CreateCustomerDTO
import com.mini.asaas.dto.customer.UpdateCustomerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import grails.gorm.transactions.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class CustomerService {

    AddressService addressService

    public Customer save(CreateCustomerDTO createCustomerDTO) {
        Customer customer = validateSave(createCustomerDTO)

        if (customer.hasErrors()) throw new ValidationException("Erro ao salvar conta", customer.errors)
 
        customer.name = createCustomerDTO.name
        customer.email = createCustomerDTO.email
        customer.cpfCnpj = createCustomerDTO.cpfCnpj
        customer.personType = CpfCnpjUtils.getPersonType(customer.cpfCnpj)
        
        customer.address = addressService.save(createCustomerDTO.addressDTO)
        
        return customer.save(failOnError: true)
   }

    public Customer update(UpdateCustomerDTO updateCustomerDTO, Long customerId) {
        Customer customer = Customer.where{
            id == customerId 
            && deleted == false
        }.first()

        Customer validatedCustomer = validateUpdate(updateCustomerDTO, customer)

        if (validatedCustomer.hasErrors()) throw new ValidationException("Erro ao editar conta", validatedCustomer.errors)
        
        validatedCustomer.name = updateCustomerDTO.name
        validatedCustomer.email = updateCustomerDTO.email
        validatedCustomer.cpfCnpj = updateCustomerDTO.cpfCnpj
        validatedCustomer.personType = CpfCnpjUtils.getPersonType(validatedCustomer.cpfCnpj)
        validatedCustomer.address = addressService.update(updateCustomerDTO.addressDTO, validatedCustomer.address.id)
        
        return validatedCustomer.save(failOnError: true)
    }

    private Customer validateSave(CreateCustomerDTO createCustomerDTO) {
        Customer customer = new Customer()
        
        if (!CpfCnpjUtils.validate(createCustomerDTO.cpfCnpj)) {
            customer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return customer
    }

    private Customer validateUpdate(UpdateCustomerDTO updateCustomerDTO, Customer customer) {
        if (!CpfCnpjUtils.validate(updateCustomerDTO.cpfCnpj)) {
            customer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }

        return customer
    }
}
