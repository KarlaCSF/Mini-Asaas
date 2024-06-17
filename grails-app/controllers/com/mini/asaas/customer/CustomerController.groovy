package com.mini.asaas.customer

import com.mini.asaas.address.AddressService
import com.mini.asaas.dto.customer.CustomerDTO
import com.mini.asaas.repositories.CustomerRepository
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
            flash.message = "Dados salvos com sucesso"
            flash.type = "success"
            redirect(action: "show", id: customer.id)
        } catch (ValidationException exception) {
            log.error("CustomerController.save >> Não foi possível salvar o Customer ${customerIdByParams}", exception)
            flash.message = "Não foi possível salvar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            flash.type = "error"
            redirect(view: "index", params: params)
        }
    }

    def edit() {
        Long customerIdByParams = params.getLong("id")
        try {
            Customer customer = CustomerRepository.findById(customerIdByParams)
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.edit >> Não foi possível buscar o Customer ${customerIdByParams}", exception)
            flash.message = "Não foi possível buscar o cliente"
            flash.type = "error"
        }
    }

    def update() {
        Long customerIdByParams = params.getLong("id")
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.update(customerDTO, customerIdByParams)
            flash.message = "Dados atualizados com sucesso"
            flash.type = "success"
            redirect(action: "edit", id: customer.id)
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer ${customerIdByParams}", exception)
            flash.message = "Não foi possível editar o cliente, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            flash.type = "error"
            redirect(action: "edit", id: customerIdByParams)
        }
    }
}
