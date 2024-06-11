package com.mini.asaas.payer

import com.mini.asaas.payer.Payer
import com.mini.asaas.payer.PayerService
import com.mini.asaas.customer.Customer
import com.mini.asaas.dto.payer.PayerDTO
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic
import com.mini.asaas.user.UserService
import grails.plugin.springsecurity.annotation.Secured

@GrailsCompileStatic
@Secured(['ROLE_ADMIN', 'ROLE_SELLER'])
class PayerController {

    PayerService payerService

    UserService userService
    
    def index() {
        return [params: params]
    }

    def create() {
        return [params: params]
    }

    def save() {
        try {
            Customer customer = userService.getCustomerByUser()
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.save(payerDTO, customer.id)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException validationException) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${params.id}", validationException)
            params.errorMessage = "Não foi possível salvar o pagador, ocorreram os seguintes erros: " + validationException.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "create", params: params)
        } catch (Exception exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${params.id}", exception)
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
            log.error("PayerController.show >> Não foi possível buscar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "index", params: params)
        }
    }

    def edit() {
        try {
            Customer customer = userService.getCustomerByUser()
            Payer payer = payerService.findByIdAndCustomerId(params.getLong("id"), customer.id, false)
            return [payer: payer]
        } catch (Exception exception) { 
            log.error("PayerController.edit >> Não foi possível buscar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "show", params: params)
        }
    }    

   def update() {
        try {
            Customer customer = userService.getCustomerByUser()
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.update(payerDTO, params.getLong("id"), customer.id)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException validationException) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${params.id}", validationException)
            params.errorMessage = "Não foi possível atualizar o pagador, ocorreram os seguintes erros: " + validationException.errors.allErrors.defaultMessage.join(", ")
            redirect(action: "edit", params: params, id: params.getLong("id"))
        } catch (Exception exception) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível atualizar o pagador"
            redirect(action: "edit", params: params, id: params.getLong("id"))
        }
    }   

    def delete() {
        try {
            Customer customer = userService.getCustomerByUser()
            payerService.delete(params.getLong("id"), customer.id)
            redirect(action: "index")
        } catch (Exception exception) {
            log.error("PayerController.delete >> Não foi possível deletar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível deletar o pagador"
            redirect(action: "show", params: params)
        }
    }

    def restore() {
        try {
            Customer customer = userService.getCustomerByUser()
            payerService.restore(params.getLong("id"), customer.id)
            redirect(action: "list")
        } catch(Exception exception) {
            log.error("PayerController.restore >> Não foi possível restaurar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível restaurar os pagadores"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        try {
            Customer customer = userService.getCustomerByUser()
            List<Payer> payerList = payerService.listByCustomer(customer.id, false)
            List<Payer> deletedPayerList = payerService.listByCustomer(customer.id, true)
            return [payerList: payerList, deletedPayerList: deletedPayerList]
        } catch(Exception exception) {
            log.error("PayerController.list >> Não foi possível listar o Payers ${params.id}", exception)
            redirect(action: "index", params: params)
        }
    }
}
