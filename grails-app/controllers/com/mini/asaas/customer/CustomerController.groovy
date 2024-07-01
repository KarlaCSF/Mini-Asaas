package com.mini.asaas.customer

import com.mini.asaas.address.AddressService
import com.mini.asaas.dto.customer.CustomerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured
import com.mini.asaas.user.UserService
import com.mini.asaas.dto.user.UserDTO
import com.mini.asaas.email.EmailService

@GrailsCompileStatic
@Secured('ROLE_ADMIN')
class CustomerController {

    CustomerService customerService

    AddressService addressService

    UserService userService

    def index() {}

    def save() {
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.save(customerDTO)
            flash.message = "Dados salvos com sucesso"
            flash.type = "success"
            redirect(action: "show")
        } catch (ValidationException exception) {
            log.error("CustomerController.save >> Não foi possível salvar o Customer", exception)
            flash.message = "Não foi possível salvar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            flash.type = "error"
            redirect(view: "index", params: params)
        }
    }

    def edit() {
        Customer customer
        try {
            customer = userService.getCurrentCustomerForLoggedUser()
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.edit >> Não foi possível buscar o Customer ${customer.id}", exception)
            flash.message = "Não foi possível buscar o cliente"
            flash.type = "error"
            redirect(action: "index")
        }
    }

    def update() {
        Customer customer
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            customer = userService.getCurrentCustomerForLoggedUser()
            customerService.update(customerDTO, customer)
            flash.message = "Dados atualizados com sucesso"
            flash.type = "success"
            redirect(action: "edit")
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer ${customer.id}", exception)
            flash.message = "Não foi possível editar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            flash.type = "error"
            redirect(action: "edit", params: params)
        }
    }
}
