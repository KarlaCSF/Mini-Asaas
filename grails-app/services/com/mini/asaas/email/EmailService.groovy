package com.mini.asaas.email

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment

@Transactional
class EmailService {

    def mailService

    void sendEmailToVerifyPayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança Pendente"
            body "Olá ${payment.payer.name}, lembre-se que existe uma cobrança pendente no valor de ${payment.value} que vencerá no dia ${payment.dueDate}."
        }
    }
}
