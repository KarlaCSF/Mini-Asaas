package com.mini.asaas.email

import com.mini.asaas.enums.email.EmailTemplateType
import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment
import grails.gsp.PageRenderer
import grails.plugins.mail.MailService

@Transactional
class EmailService {

    MailService mailService

    PageRenderer groovyPageRenderer

    public void notifyOnCreatePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Olá uma cobrança foi criada pra você"
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_CREATE, payment)
    }

    public void notifyOnUpdatePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança Atualizada"
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_UPDATE, payment)
    }

    public void notifyOnDeletePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança excluída"
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_DELETE, payment)
    }

    public void sendEmailToVerifyPayment(Payment payment) {
        mailService.sendMail {
            to payment.payer.email
            subject "Cobrança Pendente"
            body "Olá ${payment.payer.name}, lembre-se que existe uma cobrança pendente no valor de ${payment.value} que vencerá no dia ${payment.dueDate}."
        }
    }

    private void sendEmail(String email, String subjectMessage, EmailTemplateType templateType, Object data) {
        try {
            String htmlContent = renderEmailTemplate(templateType, data)
            mailService.sendMail {
                to email
                subject subjectMessage
                html htmlContent
            }
        } catch (Exception exception) {
            log.error("EmailService.sendEmail >> não foi possivel enviar o email", exception)
        }
    }

    private String renderEmailTemplate(EmailTemplateType templateType, Object data) {
        return groovyPageRenderer.render(view: templateType.getViewPath(), model: [data: data])
    }
}
