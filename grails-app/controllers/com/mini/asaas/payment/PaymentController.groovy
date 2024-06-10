package com.mini.asaas.payment

import com.mini.asaas.customer.Customer
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.payer.Payer
import com.mini.asaas.payer.PayerService
import com.mini.asaas.repositories.PaymentRepository

class PaymentController {

    PayerService payerService

    PaymentService paymentService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication

    def index() {
        return [view: "index"]
    }

    def create() {
        try {
            Boolean deletedOnly = false
            List<Payer> listPayersByCustomer = PayerRepository.listByCustomer(customer.id, deletedOnly)
            return [view: "create", listPayersByCustomer: listPayersByCustomer]
        } catch (Exception exception) {
            log.error(exception.message, exception)
        }
    }

    def edit() {
        Long paymentIdByParams = params.getLong("id")
        try {
            Payment payment = PaymentRepository.findByIdAndCustomerId(paymentIdByParams, customer.id)
            return [payment: payment, id: payment.id]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

    def show() {
        Long paymentIdByParams = params.getLong("id")
        try {
            Payment payment = PaymentRepository.findByIdAndCustomerId(paymentIdByParams, customer.id)
            return [payment: payment]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            render "Cobrança não encontrada"
        }
    }

    def save() {
        Long paymentIdByParams = params.getLong("id")
        try {
            CreatePaymentDTO createPaymentDTO = new CreatePaymentDTO(params)
            Payment payment = paymentService.save(createPaymentDTO, customer.id)
            redirect(action: "show", id: payment.id)
        } catch (Exception exception) {
            log.error("PaymentController.save >> Não foi possível salvar a Payment ${paymentIdByParams}", exception)
            params.errorMessage = "Não foi possível realizar a cobrança"
            redirect(action: "create", params: params)
        }
    }

    def update() {
        Long paymentIdByParams = params.getLong("id")
        try {
            UpdatePaymentDTO updatePaymentDTO = new UpdatePaymentDTO(params)
            Payment payment = paymentService.update(updatePaymentDTO, paymentIdByParams, customer.id)
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
        Long paymentIdByParams = params.getLong("id")
        try {
            paymentService.delete(paymentIdByParams, customer.id)
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
