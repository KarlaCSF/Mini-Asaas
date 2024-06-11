package com.mini.asaas.customer

import com.mini.asaas.address.AddressService
import com.mini.asaas.dto.customer.CustomerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured
import com.mini.asaas.user.User
import com.mini.asaas.repositories.UserRepository

@GrailsCompileStatic
@Secured('ROLE_ADMIN')
class CustomerController {

    CustomerService customerService

    AddressService addressService

    def index() {}

    def save() {
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.save(customerDTO)
            redirect(action: "show", id: customer.id)
        } catch (ValidationException exception) {
            log.error("CustomerController.save >> Não foi possível salvar o Customer ${params.id}", exception)
            params.errorMessage = "Não foi possível salvar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Customer customer = Customer.get(params.getLong("id"))
            List<User> userList = UserRepository.listByCustomer(customer.id)
            return [customer: customer, userList: userList]
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
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.update(customerDTO, params.getLong("id"))
            redirect(action: "show", id: customer.id)
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer ${params.id}", exception)
            params.errorMessage = "Não foi possível editar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "edit", params: params, id: params.getLong("id"))
        }
    }
}
