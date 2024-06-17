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
            redirect(action: "show")
        } catch (ValidationException exception) {
            log.error("CustomerController.save >> Não foi possível salvar o Customer", exception)
            params.errorMessage = "Não foi possível salvar o cliente"
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Customer customer = userService.getCurrentCustomerForLoggedUser()
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.show >> Não foi possível buscar o Customer", exception)
            render("Não foi possível buscar o Cliente")
        }
    }

    def edit() {
        try {
            Customer customer = userService.getCurrentCustomerForLoggedUser()
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.edit >> Não foi possível buscar o Customer", exception)
            params.errorMessage = "Não foi possível buscar o cliente"
            redirect(view: "index", params: params)
        }
    }

    def update() {
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = userService.getCurrentCustomerForLoggedUser()
            customerService.update(customerDTO, customer)
            redirect(action: "show")
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer", exception)
            params.errorMessage = "Não foi possível editar o cliente"
            redirect(action: "edit", params: params)
        }
    }
}
