package com.mini.asaas.email

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.repositories.PaymentRepository

@Transactional
class EmailService {

    def mailService

    public void sendEmailToVerifyPayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança Pendente"
            body "Olá ${payment.payer.name}, lembre-se que existe uma cobrança pendente no valor de ${payment.value} que vencerá no dia ${payment.dueDate}."
        }
    }

    public void fetchWaitingPaymentsAndRemindPayer() {
        List<Payment> payments = PaymentRepository.listByStatus(PaymentStatus.WAITING)

        payments.each { payment ->
            sendEmailToVerifyPayment(payment)
        }
    }
}
