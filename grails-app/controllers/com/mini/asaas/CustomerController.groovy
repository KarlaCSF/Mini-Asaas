package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address

class CustomerController {

   CustomerService customerService
   
   AddressService addressService

   def index() {}

   def save() {
      try {
         String name = params.name
         String email = params.email
         String cpfCnpj = params.cpfCnpj

         String cep = params.cep
         String city = params.city
         String state = params.state
         String district = params.district
         String street = params.street
         String number = params.number
         String complement = params.complement
         Address address = addressService.save(cep, city, state, district, street, number, complement)
         
         Customer customer = customerService.save(name, email, cpfCnpj, address)
         redirect(action: "show", id: customer.id)
      } catch (Exception e) {
         println(e)
         render "Não foi possível salvar"
      }
   }

   def show() {
      try {
         Customer customer = Customer.get(params.id)
         if (!customer) {
               render "Cliente não encontrado"
         }
         return [customer: customer]
      } catch (Exception e) {
         render "Cliente não encontrado"
      }
   }
}