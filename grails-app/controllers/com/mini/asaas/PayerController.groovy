package com.mini.asaas
import com.mini.asaas.Payer

class PayerController {

    PayerService payerService

    def index() {
        render(view: "index")
    }

    def save() {
        try {
            String name = params.name
            String email = params.email
            String cpfCnpj = params.cpfCnpj
            Payer payer = payerService.save(name, email, cpfCnpj)
            redirect(action: "show", id: payer.id)
        } catch (Exception exception) {
            log.error("PayerController.save >> Não foi possível salvar o Payer ${params.id}", exception)
            redirect(action: "index", params: params)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) {
                log.error("PayerController.show >> Não foi possível buscar o Payer ${params.id}", exception) //  Todo: switch to throw a new exception
                render "Pagador não encontrado"
            }
            return [payer: payer]
        } catch (Exception exception) {
            log.error("PayerController.show >> Não foi possível buscar o Payer ${params.id}", exception)
            render "Pagador não encontrado"
        }
    }

}
