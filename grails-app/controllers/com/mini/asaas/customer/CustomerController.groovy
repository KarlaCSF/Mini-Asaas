package com.mini.asaas.customer

import com.mini.asaas.address.AddressService
import com.mini.asaas.dto.customer.CustomerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException

@GrailsCompileStatic
class CustomerController {

    CustomerService customerService

    AddressService addressService


    def index() {}

    def save() {
        Long customerIdByParams = params.getLong("id")
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.save(customerDTO)
            redirect(action: "show", id: customer.id)
        } catch (ValidationException exception) {
            log.error("CustomerController.save >> Não foi possível salvar o Customer ${customerIdByParams}", exception)
            params.errorMessage = "Não foi possível salvar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(view: "index", params: params)
        }
    }

    def show() {
        Long customerIdByParams = params.getLong("id")
        try {
            Customer customer = customerService.findById(customerIdByParams)
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.show >> Não foi possível buscar o Customer ${customerIdByParams}", exception)
        }
    }

    def edit() {
        Long customerIdByParams = params.getLong("id")
        try {
            Customer customer = customerService.findById(customerIdByParams)
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.edit >> Não foi possível buscar o Customer ${customerIdByParams}", exception)
        }
    }

    def update() {
        Long customerIdByParams = params.getLong("id")
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.update(customerDTO, customerIdByParams)
            redirect(action: "show", id: customer.id)
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer ${customerIdByParams}", exception)
            params.errorMessage = "Não foi possível editar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "edit", params: params, id: customerIdByParams)
        }
    }
}
