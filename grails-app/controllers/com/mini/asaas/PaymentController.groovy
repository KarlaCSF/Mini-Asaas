package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.PaymentService

class PaymentController {

    PaymentService paymentService

    def index() {  
        return [params: params]
    }

    def save() {
        try {
            CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO(params)
            Long customerId = new Long(1) // todo: fix customer Id in 1 while don't have authentication
            Payment payment = paymentService.save(createPaymentDTO, customerId)
            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error("PaymentController.save >> Não foi possível salvar a Payment ${params.id}", exception)
            params.errorMessage = "Não foi possível realizar a cobrança"
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Payment payment = Payment.get(params.id)
            if (!payment) throw new Exception("PaymentController.show >> Não foi possível buscar o Payment ${params.id}")
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

}
