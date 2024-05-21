package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.Customer
import com.mini.asaas.dto.PayerDTO

class PayerController {

    PayerService payerService

    AddressService addressService
    
    def index() {
        return [params: params]
    }

    def save() {
        try {
            PayerDTO payerDTO = new PayerDTO(params)
            Long customerId = new Long(1) // todo: fix customer Id in 1 while don't have authentication
            Payer payer = payerService.save(payerDTO, customerId)
            redirect(action: "show", id: payer.id)
        } catch (Exception exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${params.id}", exception)
            params.errorMessage = "Não foi possível salvar o pagador, existem campos inválidos"
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) throw new Exception("Payer não encontrado")
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${params.id}", exception)
            render "Pagador não encontrado"
        }
    }

}
