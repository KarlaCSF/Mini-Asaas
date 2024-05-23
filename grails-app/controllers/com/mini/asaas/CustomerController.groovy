package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.dto.CustomerDto
import grails.validation.ValidationException

class CustomerController {

   CustomerService customerService
   
   AddressService addressService

   def index() {}

   def save() {
      try {
         CustomerDto customerDto = new CustomerDto(params)
         Customer customer = customerService.save(customerDto)
         redirect(action: "show", id: customer.id)
      } catch (Exception exception) {
         log.error("CustomerController.save >> Não foi possível salvar o Customer", exception)
         render("Não foi possível salvar a conta, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", "))
      }
   }

   def show() {
      try {
         Customer customer = Customer.get(params.id)
         return [customer: customer]
      } catch (Exception exception) { 
         log.error("CustomerController.show >> Não foi possível buscar o Customer ${params.id}", exception)
      }
   }
}