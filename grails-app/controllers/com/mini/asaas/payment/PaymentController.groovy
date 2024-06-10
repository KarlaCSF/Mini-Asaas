package com.mini.asaas.payment

import com.mini.asaas.payer.Payer
import com.mini.asaas.customer.Customer
import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.payment.PaymentService
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.payer.PayerService

class PaymentController {

    PaymentService paymentService
    PayerService payerService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication
    
    def index() {
        return [view: "index"]
    }

    def create() {  
        try{
            List<Payer> listPayersByCustomer = payerService.listByCustomer(customer.id, false)
            return [view: "create", listPayersByCustomer: listPayersByCustomer]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível buscar os pagadores"
            redirect(action: "create", params: params)
        }
    }

    def edit() {
        try {
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id, false)
            return [payment: payment, id: payment.id]  
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

    def show() {
        try {
            Payment payment = paymentService.findByIdAndCustomerId(params.getLong("id"), customer.id, false)
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

    def delete() {
        try {
            paymentService.delete(params.getLong("id"), customer.id)
            redirect(action: "index")
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "show", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível apagar a cobrança"
            redirect(action: "edit", params: params)
        }
    }

    def restore() {
        try {
            paymentService.restore(params.getLong("id"), customer.id)
            redirect(action: "list")
        } catch(Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível restaurar a cobrança"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        try {
            List<Payment> paymentList = paymentService.listByCustomer(customer.id, false)
            List<Payment> deletedPaymentList = paymentService.listByCustomer(customer.id, true)
            return [paymentList: paymentList, deletedPaymentList: deletedPaymentList]
        } catch(Exception exception) {
            log.error(exception.message, exception)
            render("Não foi possível listar as cobranças")
        }
    }
}
