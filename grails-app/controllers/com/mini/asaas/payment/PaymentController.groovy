package com.mini.asaas.payment

import com.mini.asaas.payer.Payer
import com.mini.asaas.customer.Customer
import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.user.UserService

class PaymentController {

    PaymentService paymentService

    UserService userService

    def index() {
        return [view: "index"]
    }

    def create() {  
        try{
            Customer customer = userService.getCustomerByUser()
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
            Customer customer = userService.getCustomerByUser()
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id)
            return [payment: payment, id: payment.id]  
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

    def show() {
        try {
            Customer customer = userService.getCustomerByUser()
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }
    
    def save() {
        try {
            Customer customer = userService.getCustomerByUser()
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
            Customer customer = userService.getCustomerByUser()
            UpdatePaymentDTO updatePaymentDTO = new UpdatePaymentDTO(params)
            Payment payment = paymentService.update(updatePaymentDTO, params.getLong("id"), customer.id)
            redirect(action: "show", id: payment.id)
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "edit", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível editar a cobrança"
            redirect(action: "edit", params: params)
        }
    }

    def delete(){
        try {
            Customer customer = userService.getCustomerByUser()
            paymentService.delete(params.getLong("id"), customer.id)
            redirect(action: "index")
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "show", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível apagar a cobrança"
            redirect(action: "show", params: params)
        }
    }
}
