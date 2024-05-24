package com.mini.asaas.payment

import com.mini.asaas.Payer
import com.mini.asaas.Customer
import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.payment.PaymentService

class PaymentController {

    PaymentService paymentService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication
    
    def index() {  
        return [params: params]
    }

    def edit() {
        try {
            Payment payment = paymentService.show(params.getLong("id"), customer.id)
            return [payment: payment, id: payment.id]  
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

    def show() {
        try {
            Payment payment = paymentService.show(params.getLong("id"), customer.id)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }
    
    def save() {
        try {
            CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO(params)
            Payment payment = paymentService.save(createPaymentDTO, customer.id)
            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error("PaymentController.save >> Não foi possível salvar a Payment ${params.id}", exception)
            params.errorMessage = "Não foi possível realizar a cobrança"
            redirect(view: "index", params: params)
        }
    }
    
    def update() {
        try {
            UpdatePaymentDTO updatePaymentDTO = new UpdatePaymentDTO(params)
            Payment payment = paymentService.update(updatePaymentDTO, params.getLong("id"), customer.id)
            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível editar a cobrança"
            redirect(view: "index", params: params)
        }
    }

    def delete(){
        try {
            paymentService.delete(params.getLong("id"), customer.id)
            redirect(view: "index")
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível apagar a cobrança"
            redirect(view: "index", params: params)
        }
    }
}