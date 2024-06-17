package com.mini.asaas.invoice

import com.mini.asaas.payment.Payment
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.customer.Customer
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.repositories.PaymentRepository
import com.mini.asaas.user.UserService
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class InvoiceController {

    PaymentService paymentService

    UserService userService

    def show() {
        Long paymentIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            Payment payment = PaymentRepository.findByIdAndCustomerId(paymentIdByParams, customerId)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render("Não foi possível carregar a cobrança.")
        }
    }

    def pay() {
        Long paymentIdByParams = params.getLong("id")
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            Payment payment = paymentService.pay(paymentIdByParams, customeId)
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
