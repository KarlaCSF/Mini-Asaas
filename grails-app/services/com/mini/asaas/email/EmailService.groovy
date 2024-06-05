package com.mini.asaas.email

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment
import grails.plugins.mail.MailService
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class EmailService {

    MailService mailService
    
    public void sendEmailOnCreatePayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança Criada - Número ${payment.id}"
            body "Olá ${payment.payer.name}, foi criada uma cobrança no valor de ${payment.value}, por ${payment.customer.name} que vencerá no dia ${payment.dueDate}."
        }
    }
    
    public void sendEmailOnUpdatePayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança atualizada - Número ${payment.id}"
            body "Olá ${payment.payer.name}, a cobrança de número ${payment.id}, vinda de ${payment.customer.name}, foi alterada."
        }
    }

    public void sendEmailOnDeletePayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança excluída - Número ${payment.id}"
            body "Olá ${payment.payer.name}, a cobrança de número ${payment.id}, no valor de ${payment.value} vinda de ${payment.customer.name} foi excluída."
        }
    }

    public void sendEmailToVerifyPayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança Pendente"
            body "Olá ${payment.payer.name}, lembre-se que existe uma cobrança pendente no valor de ${payment.value} que vencerá no dia ${payment.dueDate}."
        }
    }
}
