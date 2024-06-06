package com.mini.asaas.payer

import com.mini.asaas.payer.Payer
import com.mini.asaas.payer.PayerService
import com.mini.asaas.Customer
import com.mini.asaas.dto.payer.PayerDTO
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class PayerController {

    PayerService payerService

    Customer customer = Customer.get(1) // fixed Customer in 1 while don't have authentication

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
        } catch (ValidationException exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${params.id}", exception.message)
            params.errorMessage = "Não foi possível salvar o pagador"
            redirect(action: "create", params: params)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.getLong("id"))
            if (!payer) throw new Exception("Payer não encontrado")
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${params.id}", exception.message)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "index", params: params)
        }
    }

    def edit() {
        try {
            Payer payer = Payer.findByIdAndCustomerId(params.getLong("id"), customer.id, false)
            return [payer: payer]
        } catch (Exception exception) { 
            log.error("PayerController.edit >> Não foi possível buscar o Payer ${params.id}", exception.message)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "index", params: params)
        }
    }    

   def update() {
        try {
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.update(payerDTO, params.getLong("id"), customer.id)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException exception) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${params.id}", exception.message)
            params.errorMessage = "Não foi possível atualizar o pagador"
            redirect(action: "edit", params: params, id: params.getLong("id"))
        }
    }

    def delete() {
        try {
            payerService.delete(params.getLong("id"), customer.id)
            redirect(action: "index")
        } catch (Exception exception) {
            log.error("PayerController.delete >> Não foi possível deletar o Payer ${params.id}", exception.message)
            params.errorMessage = "Não foi possível deletar o pagador"
            redirect(action: "show", params: params)
        }
    }

    def restore() {
        try {
            payerService.restore(params.getLong("id"), customer.id)
            redirect(action: "list")
        } catch(Exception exception) {
            log.error("PayerController.restore >> Não foi possível restaurar o Payer ${params.id}", exception.message)
            params.errorMessage = "Não foi possível restaurar os pagadores"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        try {
            List<Payer> payerList = payerService.listByCustomer(customer.id, false)
            List<Payer> deletedPayerList = payerService.listByCustomer(customer.id, true)
            return [payerList: payerList, deletedPayerList: deletedPayerList]
        } catch(Exception exception) {
            log.error("PayerController.list >> Não foi possível listar o Payers ${params.id}", exception.message)
            redirect(action: "index", params: params)
        }
    }
}
