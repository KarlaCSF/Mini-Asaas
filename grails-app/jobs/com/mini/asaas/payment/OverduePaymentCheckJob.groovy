package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.PaymentStatus

class OverduePaymentCheckJob {
  
  PaymentService paymentService

  static triggers = {
    cron name: 'overduePaymentCheckTrigger', cronExpression: "0 0/1 * * * ?"
  }

  @Transactional
  def execute() {
    fetchAndProcessOverduePayments()
  }
  
  private void fetchAndProcessOverduePayments () {
    List<Payment> payments = Payment.findAllByStatus(PaymentStatus.WAITING)

    payments.each { payment ->
      paymentService.checkAndUpdateOverduePaymentStatus(payment)
    }
  }
}
