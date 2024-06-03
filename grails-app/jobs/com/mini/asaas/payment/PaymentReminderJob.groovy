package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.email.EmailService

class PaymentReminderJob {

  EmailService emailService

  static triggers = {
    cron name: 'paymentReminderTrigger', cronExpression: "0 0 7 * * ?"
  }

  @Transactional
  def execute() {
    List<Payment> payments = Payment.findAllByStatus(PaymentStatus.WAITING)

    payments.each { payment ->
      emailService.sendEmailToVerifyPayment(payment)
    }
  }
}
