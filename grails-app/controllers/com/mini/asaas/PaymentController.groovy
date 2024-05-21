package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.enums.PaymentType
import com.mini.asaas.dto.PaymentDTO
import com.mini.asaas.PaymentService

class PaymentController {

    PaymentService paymentService

    def index() {  
        List<Payer> payerList = Payer.list()
        return [params: params, payerList: payerList, paymentTypes: PaymentType.values()]
    }

    def save() {
        try {
            PaymentDTO paymentDTO = new PaymentDTO(params)
            Long customerId = new Long(1) // todo: fix customer Id in 1 while don't have authentication
            Payment payment = paymentService.save(paymentDTO,customerId)
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
            if (!payment) throw new Exception("Payment não encontrado")
            return [payment: payment]
        } catch (Exception exception) {
            log.error("PaymentController.show >> Não foi possível buscar o Payment ${params.id}", exception)
            render "Cobrança não encontrada"
        }
    }

}
