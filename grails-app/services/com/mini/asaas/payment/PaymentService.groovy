package com.mini.asaas.payment

import com.mini.asaas.customer.CustomerService
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.email.EmailService
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.payer.PayerService
import com.mini.asaas.repositories.CustomerRepository
import com.mini.asaas.repositories.PayerRepository
import com.mini.asaas.repositories.PaymentRepository
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@GrailsCompileStatic
@Transactional
class PaymentService {

    CustomerService customerService

    EmailService emailService

    PayerService payerService

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

        return payment.save(failOnError: true)
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

    private void updateStatusToOverdueIfPossible(Payment payment) {
        if (!verifyIfOverdue(payment)) return

        payment.status = PaymentStatus.OVERDUE;
        payment.save();
    }
}
