package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.Customer
import com.mini.asaas.dto.PayerDto

class PayerController {

    PayerService payerService

    AddressService addressService
    
    def index() {
        List<Customer> customerList = Customer.list()
        return [params: params, customerList: customerList]
    }

    def save() {
        try {
            PayerDto payerDto = new PayerDto(params)
            Payer payer = payerService.save(payerDto)
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
