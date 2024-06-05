package com.mini.asaas.payment

import com.mini.asaas.payment.PaymentService

class PaymentReminderJob {

  PaymentService paymentService

  static triggers = {
    cron name: 'PaymentReminderJobTrigger', cronExpression: "0 0 7 * * ?", startDelay: 10000
  }

  def execute() {
    paymentService.notifyWaitingPayments()
  }
}
