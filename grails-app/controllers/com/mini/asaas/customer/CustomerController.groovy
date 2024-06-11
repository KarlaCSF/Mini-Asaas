package com.mini.asaas.customer

import com.mini.asaas.address.AddressService
import com.mini.asaas.dto.customer.CustomerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured
import com.mini.asaas.user.User
import com.mini.asaas.repositories.UserRepository
import com.mini.asaas.user.UserService
import grails.plugin.springsecurity.SpringSecurityService

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
            params.errorMessage = "Não foi possível salvar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Customer customer = userService.getCustomerByUser()
            List<User> userList = UserRepository.listByCustomer(customer.id)
            return [customer: customer, userList: userList]
        } catch (Exception exception) {
            log.error("CustomerController.show >> Não foi possível buscar o Customer", exception)
        }
    }

    def edit() {
        try {
            Customer customer = userService.getCustomerByUser()
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.edit >> Não foi possível buscar o Customer", exception)
        }
    }

    def update() {
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = userService.getCustomerByUser()
            customerService.update(customerDTO, customer)
            redirect(action: "show")
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer", exception)
            params.errorMessage = "Não foi possível editar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "edit", params: params)
        }
    }
}
