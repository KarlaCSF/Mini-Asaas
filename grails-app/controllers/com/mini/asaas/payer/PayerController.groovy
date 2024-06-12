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
            Customer customer = userService.getCustomerByUser()
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
        Long payerIdByParams = params.getLong("id")
        try {
            Boolean deletedOnly = false
            Payer payer = PayerRepository.findByIdAndCustomerId(payerIdByParams, customer.id, deletedOnly)
            if (!payer) throw new Exception("Payer não encontrado")
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "index", params: params)
        }
    }

    def edit() {
        Long payerIdByParams = params.getLong("id")
        try {
            Customer customer = userService.getCustomerByUser()
            Payer payer = PayerRepository.findByIdAndCustomerId(payerIdByParams, customer.id, false)
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.edit >> Não foi possível buscar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível buscar o pagador"
            redirect(action: "show", params: params)
        }
    }

    def update() {
        Long payerIdByParams = params.getLong("id")
        try {
            Customer customer = userService.getCustomerByUser()
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
        Long payerIdByParams = params.getLong("id")
        try {
            Customer customer = userService.getCustomerByUser()
            payerService.delete(payerIdByParams, customer.id)
            redirect(action: "index")
        } catch (Exception exception) {
            log.error("PayerController.delete >> Não foi possível deletar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível deletar o pagador"
            redirect(action: "show", params: params)
        }
    }

    def restore() {
        Long payerIdByParams = params.getLong("id")
        try {
            Customer customer = userService.getCustomerByUser()
            payerService.restore(payerIdByParams, customer.id)
            redirect(action: "list")
        } catch (Exception exception) {
            log.error("PayerController.restore >> Não foi possível restaurar o Payer ${payerIdByParams}", exception)
            params.errorMessage = "Não foi possível restaurar os pagadores"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        Long payerIdByParams = params.getLong("id")
        try {
            Customer customer = userService.getCustomerByUser()
            List<Payer> payerList = PayerRepository.listByCustomer(customer.id, false)
            List<Payer> deletedPayerList = PayerRepository.listByCustomer(customer.id, true)
            return [payerList: payerList, deletedPayerList: deletedPayerList]
        } catch (Exception exception) {
            log.error("PayerController.list >> Não foi possível listar o Payers ${payerIdByParams}", exception)
            redirect(action: "index", params: params)
        }
    }
}
