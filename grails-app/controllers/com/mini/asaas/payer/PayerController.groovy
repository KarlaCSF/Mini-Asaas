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

    def save() {
        try {
            PayerDTO payerDTO = new PayerDTO(params)
            Payer payer = payerService.save(payerDTO, customer.id)
            redirect(action: "show", id: payer.id)
        } catch (ValidationException exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível salvar o pagador, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.getLong("id"))
            if (!payer) throw new Exception("Payer não encontrado")
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${params.id}", exception)
            render "Pagador não encontrado"
        }
    }

    def edit() {
      try {
         Payer payer = Payer.get(params.getLong("id"))
         return [payer: payer]
      } catch (Exception exception) { 
         log.error("PayerController.edit >> Não foi possível buscar o Payer ${params.id}", exception)
      }
   }

   def update() {
      try {
         PayerDTO payerDTO = new PayerDTO(params)
         Payer payer = payerService.update(payerDTO, params.getLong("id"), customer.id)
         redirect(action: "show", id: payer.id)
      } catch (ValidationException exception) {
         log.error("PayerController.update >> Não foi possível atualizar o Payer ${params.id}", exception)
         params.errorMessage = "Não foi possível editar o pagador, ocorreram os seguintes erros: " + exception.errors.allErrors.defaultMessage.join(", ")
         redirect(action: "edit", params: params, id: params.getLong("id"))
      }
   }
}
