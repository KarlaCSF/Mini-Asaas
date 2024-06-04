package com.mini.asaas.invoice

import com.mini.asaas.payment.Payment
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.Customer

class InvoiceController {

    PaymentService paymentService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication

    def index() {
    }

    def show() {
        try {
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render exception.message
        }
    }

    def pay() {
        try {
            Payment payment = paymentService.pay(params.getLong("id"), customer.id)
            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "show", params: params)
        }
    }
}