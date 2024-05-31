package com.mini.asaas.email

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment

@Transactional
class EmailService {

    def mailService

    void enviarEmailVerificacao(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Verificação de Status do Pagamento"
            body "Caro cliente, por favor verifique o status do seu pagamento referente ao pedido ${payment.id}."
        }
    }
}
