package com.mini.asaas.payer

import com.mini.asaas.customer.Customer
import com.mini.asaas.customer.CustomerService
import com.mini.asaas.dto.payer.PayerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException

@GrailsCompileStatic
class PayerController {

    CustomerService customerService

    PayerService payerService

    Customer customer = Customer.get(1) // fixed Customer in 1 while don't have authentication

    Long payerIdByParams = params.getLong("id")

    def index() {
        return [params: params]
    }

    def create() {
        return [params: params]
    }

    def save() {
        try {
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.save(payerDTO, customer.id)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException validationException) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${payerIdByParams}", validationException)
            params.errorMessage = "Não foi possível salvar o pagador, ocorreram os seguintes erros: " + validationException.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "create", params: params)
        } catch (Exception exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível salvar o pagador"
            redirect(action: "create", params: params)
        }
    }

    def show() {
        try {
            Boolean deletedOnly = false
            Payer payer = payerService.findByIdAndCustomerId(payerIdByParams, customer.id, deletedOnly)
            if (!payer) throw new Exception("Payer não encontrado")
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "index", params: params)
        }
    }

    def edit() {
        try {
            Payer payer = payerService.findByIdAndCustomerId(payerIdByParams, customer.id, false)
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.edit >> Não foi possível buscar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "show", params: params)
        }
    }

    def update() {
        try {
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.update(payerDTO, payerIdByParams, customer.id)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException validationException) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${payerIdByParams}", validationException)
            params.errorMessage = "Não foi possível atualizar o pagador, ocorreram os seguintes erros: " + validationException.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "edit", params: params, id: payerIdByParams)
        } catch (Exception exception) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível atualizar o pagador"
            redirect(action: "edit", params: params, id: payerIdByParams)
        }
    }

    def delete() {
        try {
            payerService.delete(payerIdByParams, customer.id)
            redirect(action: "index")
        } catch (Exception exception) {
            log.error("PayerController.delete >> Não foi possível deletar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível deletar o pagador"
            redirect(action: "show", params: params)
        }
    }

    def restore() {
        try {
            payerService.restore(payerIdByParams, customer.id)
            redirect(action: "list")
        } catch (Exception exception) {
            log.error("PayerController.restore >> Não foi possível restaurar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível restaurar os pagadores"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        try {
            List<Payer> payerList = payerService.listByCustomer(customer.id, false)
            List<Payer> deletedPayerList = payerService.listByCustomer(customer.id, true)
            return [payerList: payerList, deletedPayerList: deletedPayerList]
        } catch (Exception exception) {
            log.error("PayerController.list >> Não foi possível listar o Payers ${payerIdByParams}", exception)
            redirect(action: "index", params: params)
        }
    }
}
