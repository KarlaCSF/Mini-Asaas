package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.dto.customer.CreateCustomerDTO
import com.mini.asaas.dto.customer.UpdateCustomerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException

@GrailsCompileStatic
class CustomerController {

   CustomerService customerService
   
   AddressService addressService

   def index() {}

   def save() {
      try {
         CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(params)
         Customer customer = customerService.save(createCustomerDTO)
         redirect(action: "show", id: customer.id)
      } catch (ValidationException exception) {
         log.error("CustomerController.save >> Não foi possível salvar o Customer", exception)
         render("Não foi possível salvar a conta, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", "))
      }
   }

   def show() {
      try {
         Customer customer = Customer.get(params.getLong("id"))
         return [customer: customer]
      } catch (Exception exception) { 
         log.error("CustomerController.show >> Não foi possível buscar o Customer ${params.id}", exception)
      }
   }

   def edit() {
      try {
         Customer customer = Customer.get(params.getLong("id"))
         return [customer: customer]
      } catch (Exception exception) { 
         log.error("CustomerController.edit >> Não foi possível buscar o Customer ${params.id}", exception)
      }
   }

   def update() {
      try {
         UpdateCustomerDTO updateCustomerDTO = new UpdateCustomerDTO(params)
         Customer customer = customerService.update(updateCustomerDTO, params.getLong("id"))
         redirect(action: "show", id: customer.id)
      } catch (Exception exception) {
         log.error(exception.message, exception)
         params.errorMessage = "Não foi possível editar a cobrança"
         redirect(view: "index", params: params)
      }
   }
}