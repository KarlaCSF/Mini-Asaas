package com.mini.asaas.email

import com.mini.asaas.enums.email.EmailTemplateType
import com.mini.asaas.payment.Payment
import com.mini.asaas.utils.PaymentUtils
import grails.gorm.transactions.Transactional
import grails.gsp.PageRenderer
import grails.plugins.mail.MailService
import grails.web.mapping.LinkGenerator

@Transactional
class EmailService {

    LinkGenerator grailsLinkGenerator
    MailService mailService
    PageRenderer groovyPageRenderer

    public void notifyOnCreatePayment(Payment payment) {
        sendPaymentNotification(payment, "uma cobrança foi criada", "Cobrança Nova", "Olá, uma cobrança foi gerada pra você")
    }

    public void notifyOnUpdatePayment(Payment payment) {
        sendPaymentNotification(payment, "uma cobrança foi atualizada", "Cobrança Atualizada", "Cobrança Atualizada")
    }

    public void notifyOnDeletePayment(Payment payment) {
        sendPaymentNotification(payment, "uma cobrança foi excluída", "Cobrança Excluída", "Cobrança Excluída")
    }

    public void notifyToVerifyPayment(Payment payment) {
        sendPaymentNotification(payment, "lembre-se que existe uma cobrança pendente", "Cobrança Pendente", "Cobrança Pendente")
    }

    public void notifyOnOverduePayment(Payment payment) {
        sendPaymentNotification(payment, "uma cobrança venceu", "Cobrança Atrasada", "Cobrança Atrasada")
    }

    private void sendPaymentNotification(Payment payment, String emailAction, String emailHeadTitle, String subjectMessage) {
        String to = payment.payer.email
        String paymentLink = grailsLinkGenerator.link(controller: 'payment', action: 'show', id: payment.id, absolute: true)
        Map properties = PaymentUtils.mapPaymentDetails(payment, paymentLink)
        properties.emailActionPayment = emailAction
        properties.emailHeadTitle = emailHeadTitle
        properties.emailSubject = subjectMessage
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_TO_PAYER, properties)
    }

    private void sendEmail(String email, String subjectMessage, EmailTemplateType templateType, Map properties) {
        try {
            String htmlContent = renderEmailTemplate(templateType, properties)
            mailService.sendMail {
                to email
                subject subjectMessage
                html htmlContent
            }
        } catch (Exception exception) {
            log.error("EmailService.sendEmail >> não foi possivel enviar o email", exception)
        }
    }

    private String renderEmailTemplate(EmailTemplateType templateType, Map properties) {
        return groovyPageRenderer.render(view: templateType.viewPath, model: [properties: properties])
    }
}
