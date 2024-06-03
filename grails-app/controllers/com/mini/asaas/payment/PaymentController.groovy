package com.mini.asaas.payment

import com.mini.asaas.Payer
import com.mini.asaas.Customer
import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.payment.PaymentService

class PaymentController {

    PaymentService paymentService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication
    
    def index() {
        return [view: "index"]
    }

    def create() {  
        try{
            List<Payer> listPayersByCustomer = Payer.where{
            customer.id == customer.id
            }.list() // while don't have a payerservice to give a list of payer from a customer 
            return [view: "create", listPayersByCustomer: listPayersByCustomer]
        } catch (Exception exception) {
            log.error(exception.message, exception)
        }
    }

    def edit() {
        try {
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id)
            return [payment: payment, id: payment.id]  
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

    def show() {
        try {
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id)
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
            redirect(action: "create", params: params)
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
            redirect(view: "edit", params: params)
        }
    }

    def delete(){
        try {
            paymentService.delete(params.getLong("id"), customer.id)
            redirect(view: "index")
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível apagar a cobrança"
            redirect(view: "edit", params: params)
        }
    }

    def pay() {
        try {
            Payment payment = paymentService.pay(params.getLong("id"), customer.id)
            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível pagar a cobrança"
            redirect(action: "show", params: params)
        }
    }
}
