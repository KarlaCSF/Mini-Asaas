package com.mini.asaas.payment

import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.email.EmailService

class PaymentReminderJob {

  EmailService emailService

  static triggers = {
    cron name: 'PaymentReminderJobTrigger', cronExpression: "0 0 7 * * ?", startDelay: 10000
  }

  def execute() {
    emailService.remindPaymentsWaiting()
  }
}
