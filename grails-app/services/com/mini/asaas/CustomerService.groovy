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
        
        customer.name = updateCustomerDTO.name
        customer.email = updateCustomerDTO.email
        customer.cpfCnpj = updateCustomerDTO.cpfCnpj
        customer.personType = CpfCnpjUtils.getPersonType(customer.cpfCnpj)
        customer.address = addressService.update(updateCustomerDTO.addressDTO, customer.address.id)
        
        return customer.save(failOnError: true)
    }

    private Customer validateSave(CreateCustomerDTO createCustomerDTO) {
        Customer customer = new Customer()
        
        if (!CpfCnpjUtils.validate(createCustomerDTO.cpfCnpj)) {
            customer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return customer
    }
}
