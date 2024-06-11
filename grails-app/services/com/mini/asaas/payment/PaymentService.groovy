package com.mini.asaas.payment

import com.mini.asaas.customer.Customer
import com.mini.asaas.customer.CustomerService
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.email.EmailService
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.notification.NotificationService
import com.mini.asaas.payer.PayerService
import com.mini.asaas.repositories.CustomerRepository
import com.mini.asaas.repositories.PayerRepository
import com.mini.asaas.repositories.PaymentRepository
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.web.mapping.LinkGenerator

@GrailsCompileStatic
@Transactional
class PaymentService {

    CustomerService customerService

    EmailService emailService

    LinkGenerator grailsLinkGenerator

    PayerService payerService

    NotificationService notificationService

    public Payment save(CreatePaymentDTO createPaymentDTO, Long customerId) {
        Payment payment = new Payment()
        Boolean deletedOnly = false

        payment.customer = CustomerRepository.findById(customerId)
        payment.payer = PayerRepository.findByIdAndCustomerId(createPaymentDTO.payerId, customerId, deletedOnly)
        payment.value = createPaymentDTO.value
        payment.dueDate = createPaymentDTO.dueDate
        payment.billingType = createPaymentDTO.billingType
        payment.status = createPaymentDTO.status

        return payment.save(failOnError: true)
    }

    public Payment update(UpdatePaymentDTO updatePaymentDTO, Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new BusinessException("Essa cobrança não pode ser modificada")

        payment.value = updatePaymentDTO.value
        payment.dueDate = updatePaymentDTO.dueDate
        payment.billingType = updatePaymentDTO.billingType

        return payment.save(failOnError: true)
    }

    public void delete(Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new BusinessException("Essa cobrança não pode ser modificada")

        payment.deleted = true

        payment.save(failOnError: true)
    }

    public Payment pay(Long paymentId, Long customerId) {
        Payment payment = PaymentRepository.findByIdAndCustomerId(paymentId, customerId)
        if (!payment.canEdit()) throw new BusinessException("Essa cobrança não pode ser modificada")

        payment.status = PaymentStatus.PAID

        payment.save(failOnError: true)
        createNotificationOnPay(payment)
        return payment
    }

    public void processOverdue() {
        List<Payment> paymentList = PaymentRepository.listByStatus(PaymentStatus.WAITING)

        paymentList.each { payment ->
            updateStatusToOverdueIfPossible(payment)
        }
    }

    public Boolean verifyIfOverdue(Payment payment) {
        final Date currentDate = new Date()
        Date dueDate = payment.dueDate
        return dueDate.before(currentDate)
    }

    public void notifyWaitingPayments() {
        List<Payment> paymentList = PaymentRepository.listByStatus(PaymentStatus.WAITING)

        paymentList.each { payment ->
            emailService.sendEmailToVerifyPayment(payment)
        }
    }

    private void createNotificationOnPay(Payment payment) {
        String title = "Cobrança paga"
        String description = "A cobrança ${payment.id} no valor de ${payment.value} feita ao ${payment.payer.name} foi paga."
        String actionLink = grailsLinkGenerator.link(controller: 'payment', action: 'show', id: payment.id, absolute: true)
        Customer customer = payment.customer
        notificationService.create(title, description, actionLink, customer)
    }

    private void updateStatusToOverdueIfPossible(Payment payment) {
        if (!verifyIfOverdue(payment)) return

        payment.status = PaymentStatus.OVERDUE;
        payment.save();
    }
}
