package com.mini.asaas.email

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.repositories.PaymentRepository
import grails.plugins.mail.MailService

@Transactional
class EmailService {

    MailService mailService

    PaymentService paymentService
    
    private void sendEmailToVerifyPayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança Pendente"
            body "Olá ${payment.payer.name}, lembre-se que existe uma cobrança pendente no valor de ${payment.value} que vencerá no dia ${payment.dueDate}."
        }
    }

    public void remindPaymentsWaiting() {
        List<Payment> paymentList = paymentService.listByStatus(PaymentStatus.WAITING)

        paymentList.each { payment ->
            sendEmailToVerifyPayment(payment)
        }
    }
}
