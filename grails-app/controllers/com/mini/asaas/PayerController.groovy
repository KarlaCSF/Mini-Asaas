package com.mini.asaas
import com.mini.asaas.Payer

class PayerController {

    PayerService payerService

    def save() {
        try {
            String name = params.name
            String email = params.email
            String cpfCnpj = params.cpfCnpj
            Payer payer = payerService.save(name, email, cpfCnpj)
            redirect(action: "show", id: payer.id)
        } catch (Exception exception) {
            redirect(action: "index", params: params)
        }
    }

    def show() {
        try {
            Payer payer = Payer.get(params.id)
            if (!payer) {
                render "Pagador não encontrado"
            }
            return [payer: payer]
        } catch (Exception exception) {
            render "Pagador não encontrado"
        }
    }

}
