package com.mini.asaas.payer

import com.mini.asaas.customer.Customer
import com.mini.asaas.customer.CustomerService
import com.mini.asaas.dto.payer.PayerDTO
import com.mini.asaas.repositories.PayerRepository
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic
import com.mini.asaas.user.UserService
import grails.plugin.springsecurity.annotation.Secured

@GrailsCompileStatic
@Secured(['ROLE_ADMIN', 'ROLE_SELLER'])
class PayerController {

    CustomerService customerService

    PayerService payerService

    UserService userService

    def index() {
        return [params: params]
    }

    def create() {
        return [params: params]
    }

    def save() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.save(payerDTO, customerId)
            flash.message = "Pagador salvo com sucesso"
            flash.type = "success"
            redirect(action: "show", id: payer.id)
        } catch (ValidationException validationException) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${payerIdByParams}", validationException)
            flash.message = "Não foi possível salvar o pagador, ocorreram os seguintes erros: " + validationException.errors.allErrors.defaultMessage.join(", ")
            flash.type = "error"
            redirect(action: "create", params: params)
        } catch (Exception exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${payerIdByParams}", exception)
            flash.message = "Não foi possível salvar o pagador"
            flash.type = "error"
            redirect(action: "create", params: params)
        }
    }

    def show() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            Boolean deletedOnly = false
            Payer payer = PayerRepository.findByIdAndCustomerId(payerIdByParams, customerId, deletedOnly)
            if (!payer) throw new Exception("Payer não encontrado")
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${payerIdByParams}", exception)
            flash.message = "Não foi possível buscar o pagador"
            flash.type = "error"
            redirect(action: "index", params: params)
        }
    }

    def edit() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            Payer payer = PayerRepository.findByIdAndCustomerId(payerIdByParams, customerId, false)
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.edit >> Não foi possível buscar o Payer ${payerIdByParams}", exception)
            flash.message = "Não foi possível buscar o pagador"
            flash.type = "error"
            redirect(action: "show", params: params)
        }
    }

    def update() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.update(payerDTO, payerIdByParams, customerId)
            flash.message = "Pagador atualizado com sucesso"
            flash.type = "success"
            redirect(action: "show", id: payer.id)
        } catch (ValidationException validationException) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${payerIdByParams}", validationException)
            flash.message = "Não foi possível atualizar o pagador, ocorreram os seguintes erros: " + validationException.errors.allErrors.defaultMessage.join(", ")
            flash.type = "error"
            redirect(action: "edit", params: params, id: payerIdByParams)
        } catch (Exception exception) {
            log.error("PayerController.update >> Não foi possível atualizar o Payer ${payerIdByParams}", exception)
            flash.message = "Não foi possível atualizar o pagador"
            flash.type = "error"
            redirect(action: "edit", params: params, id: payerIdByParams)
        }
    }

    def delete() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            payerService.delete(payerIdByParams, customerId)
            redirect(action: "index")
        } catch (Exception exception) {
            log.error("PayerController.delete >> Não foi possível deletar o Payer ${payerIdByParams}", exception)
            flash.message = "Não foi possível deletar o pagador"
            flash.type = "error"
            redirect(action: "show", params: params)
        }
    }

    def restore() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            payerService.restore(payerIdByParams, customerId)
            redirect(action: "list")
        } catch (Exception exception) {
            log.error("PayerController.restore >> Não foi possível restaurar o Payer ${payerIdByParams}", exception)
            flash.message = "Não foi possível restaurar os pagadores"
            flash.type = "error"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        Long payerIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            List<Payer> payerList = PayerRepository.listByCustomer(customerId, false)
            List<Payer> deletedPayerList = PayerRepository.listByCustomer(customerId, true)
            return [payerList: payerList, deletedPayerList: deletedPayerList]
        } catch (Exception exception) {
            log.error("PayerController.list >> Não foi possível listar o Payers ${payerIdByParams}", exception)
            flash.message = "Não foi possível listar os pagadores"
            flash.type = "error"
            redirect(action: "index", params: params)
        }
    }
}
