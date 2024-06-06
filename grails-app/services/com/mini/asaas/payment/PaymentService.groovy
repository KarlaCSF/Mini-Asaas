package com.mini.asaas.payment

import com.mini.asaas.Customer
import com.mini.asaas.email.EmailService
import com.mini.asaas.Payer
import com.mini.asaas.enums.email.EmailTemplateType
import com.mini.asaas.payment.Payment
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.repositories.PaymentRepository
import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class PaymentService {

    EmailService emailService

    public Payment save(CreatePaymentDTO createPaymentDTO, Long customerId) {
        Payment payment = new Payment()

        payment.customer = Customer.where {
            id == customerId
                    && deleted == false
        }.first()

        payment.payer = Payer.where {
            id == createPaymentDTO.payerId
                    && customer.id == customerId
                    && deleted == false
        }.first()

        payment.value = createPaymentDTO.value
        payment.dueDate = createPaymentDTO.dueDate
        payment.billingType = createPaymentDTO.billingType
        payment.status = createPaymentDTO.status

        payment.save(failOnError: true)
        notifyOnCreatePayment(payment)
        return payment
    }

    public Payment update(UpdatePaymentDTO updatePaymentDTO, Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new Exception("Essa cobrança não pode ser modificada")
        payment.value = updatePaymentDTO.value
        payment.dueDate = updatePaymentDTO.dueDate
        payment.billingType = updatePaymentDTO.billingType

        payment.save(failOnError: true)
        notifyOnUpdatePayment(payment)

        return payment
    }

    public Payment findByIdAndCustomerId(Long paymentId, Long customerId) {
        return PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
    }

    public void delete(Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new Exception("Essa cobrança não pode ser modificada")
        payment.deleted = true

        payment.save(failOnError: true)
        notifyOnDeletePayment(payment)
    }

    public List<Payment> listByCustomer(Long customerId) {
        return PaymentRepository.listByCustomer(customerId)
    }

    public List<Payment> listByStatus(PaymentStatus status) {
        return PaymentRepository.listByStatus(status)
    }

    public void notifyWaitingPayments() {
        List<Payment> paymentList = listByStatus(PaymentStatus.WAITING)

        paymentList.each { payment ->
            emailService.sendEmailToVerifyPayment(payment)
        }
    }

    private void notifyOnCreatePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Olá uma cobrança foi criada pra você"
        emailService.sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_CREATE, payment)
    }

    private void notifyOnUpdatePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança Atualizada"
        emailService.sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_UPDATE, payment)
    }

    private void notifyOnDeletePayment(Payment payment) {
        String to = payment.payer.email
        String subjectMessage = "Cobrança excluída"
        emailService.sendEmail(to, subjectMessage, EmailTemplateType.PAYMENT_DELETE, payment)
    }
}
