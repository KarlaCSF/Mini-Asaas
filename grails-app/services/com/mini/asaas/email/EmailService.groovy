package com.mini.asaas.email

import com.mini.asaas.enums.email.EmailTemplateType
import com.mini.asaas.payment.Payment
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
        String to = payment.payer.email
        String subjectMessage = "Olá, uma cobrança foi gerada pra você"
        String paymentLink = grailsLinkGenerator.link(controller: 'payment', action: 'show', id: payment.id, absolute: true)
        Map properties = [
                customerCity   : payment.customer.address.city,
                customerCpfCnpj: payment.customer.cpfCnpj,
                customerEmail  : payment.customer.email,
                customerName   : payment.customer.name,
                customerState  : payment.customer.address.state,
                payerName      : payment.payer.name,
                paymentDueDate : payment.dueDate,
                paymentLink    : paymentLink,
                paymentValue   : payment.value
        ]
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_CREATE, properties)
    }

    public void notifyOnUpdatePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança Atualizada"
        String paymentLink = grailsLinkGenerator.link(controller: 'payment', action: 'show', id: payment.id, absolute: true)
        Map properties = [
                customerCity   : payment.customer.address.city,
                customerCpfCnpj: payment.customer.cpfCnpj,
                customerEmail  : payment.customer.email,
                customerName   : payment.customer.name,
                customerState  : payment.customer.address.state,
                payerName      : payment.payer.name,
                paymentDueDate : payment.dueDate,
                paymentLink    : paymentLink,
                paymentValue   : payment.value
        ]
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_UPDATE, properties)
    }

    public void notifyOnDeletePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança Excluída"
        Map properties = [
                customerCity   : payment.customer.address.city,
                customerCpfCnpj: payment.customer.cpfCnpj,
                customerEmail  : payment.customer.email,
                customerName   : payment.customer.name,
                customerState  : payment.customer.address.state,
                payerName      : payment.payer.name,
                paymentDueDate : payment.dueDate,
                paymentValue   : payment.value
        ]
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_DELETE, properties)
    }

    public void notifyToVerifyPayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança Pendente"
        String paymentLink = grailsLinkGenerator.link(controller: 'payment', action: 'show', id: payment.id, absolute: true)
        Map properties = [
                customerCity   : payment.customer.address.city,
                customerCpfCnpj: payment.customer.cpfCnpj,
                customerEmail  : payment.customer.email,
                customerName   : payment.customer.name,
                customerState  : payment.customer.address.state,
                payerName      : payment.payer.name,
                paymentDueDate : payment.dueDate,
                paymentLink    : paymentLink,
                paymentValue   : payment.value
        ]
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_VERIFY, properties)
    }

    public void notifyOnOverduePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança Atrasada"
        String paymentLink = grailsLinkGenerator.link(controller: 'payment', action: 'show', id: payment.id, absolute: true)
        Map properties = [
                customerCity   : payment.customer.address.city,
                customerCpfCnpj: payment.customer.cpfCnpj,
                customerEmail  : payment.customer.email,
                customerName   : payment.customer.name,
                customerState  : payment.customer.address.state,
                payerName      : payment.payer.name,
                paymentDueDate : payment.dueDate,
                paymentLink    : paymentLink,
                paymentValue   : payment.value
        ]
        sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_OVERDUE, properties)
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
