package com.mini.asaas.payment

import grails.gorm.transactions.Transactional
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.enums.payment.PaymentStatus

class UpdatePaymentStatusToOverdueJob {
  
  PaymentService paymentService

  static triggers = {
    cron name: 'UpdatePaymentStatusToOverdueJobTrigger', cronExpression: "0 0 0 * * ?", startDelay: 10000
  }

  @Transactional
  def execute() {
    paymentService.processOverdue()
  } 
}
