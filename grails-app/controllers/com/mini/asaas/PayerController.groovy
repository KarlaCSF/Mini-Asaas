package com.mini.asaas
import com.mini.asaas.Payer

class PayerController {

    def PayerService

    def index() {
        
    }

    def save() {
        try {
            String name = params.name
            String email = params.email
            String cpfCnpj = params.cpfCnpj
            String address = params.address
            String password = params.password
            Payer payer = payerService.save(name, email, cpfCnpj, address, password)
            redirect(action: "show", id: payer.id)
        } catch (Exception e) {
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
        } catch (Exception e) {
            render "Pagador não encontrado"
        }
    }

}
