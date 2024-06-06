package com.mini.asaas.invoice

import com.mini.asaas.payment.Payment
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.customer.Customer
import com.mini.asaas.exception.BusinessException

class InvoiceController {

    PaymentService paymentService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication

    def show() {
        try {
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render("Não foi possível carregar a cobrança.")
        }
    }

    def pay() {
        try {
            Payment payment = paymentService.pay(params.getLong("id"), customer.id)
            redirect(action: "show", id: payment.id)
        } catch (BusinessException businessException) {
            log.error(businessException.message, businessException)
            params.errorMessage = businessException.message
            redirect(action: "show", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível pagar a cobrança"
            redirect(action: "show", params: params)
        }
    }
}
