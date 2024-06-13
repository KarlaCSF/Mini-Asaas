package com.mini.asaas.payment

import com.mini.asaas.customer.Customer
import com.mini.asaas.dto.payment.CreatePaymentDTO
import com.mini.asaas.dto.payment.UpdatePaymentDTO
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.payer.Payer
import com.mini.asaas.payer.PayerService
import com.mini.asaas.repositories.PayerRepository
import com.mini.asaas.repositories.PaymentRepository
import com.mini.asaas.repositories.PayerRepository

class PaymentController {

    PayerService payerService

    PaymentService paymentService

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication

    def index() {
        return [view: "index", customerId: customer.id]
    }

    def create() {
        try {
            Boolean deletedOnly = false
            List<Payer> listPayersByCustomer = PayerRepository.listByCustomer(customer.id, deletedOnly)
            return [view: "create", listPayersByCustomer: listPayersByCustomer]
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível buscar os pagadores"
            redirect(action: "create", params: params)
        }
    }

    def edit() {
        Long paymentIdByParams = params.getLong("id")
        Boolean deletedOnly = false
        try {
            Payment payment = PaymentRepository.findByIdAndCustomerId(paymentIdByParams, customer.id, deletedOnly)
            return [payment: payment, id: payment.id]
        } catch (Exception exception) {
            log.error("PaymentController.edit >> Não foi possível buscar a Payment ${paymentIdByParams}", exception)
            params.errorMessage = "Não foi possível buscar a cobrança"
            redirect(action: "show", params: params)
        }
    }

    def show() {
        Long paymentIdByParams = params.getLong("id")
        Boolean deletedOnly = false
        try {
            Payment payment = PaymentRepository.findByIdAndCustomerId(paymentIdByParams, customer.id, deletedOnly)
            return [payment: payment]
        } catch (Exception exception) {
            log.error("PaymentController.show >> Não foi possível buscar a Payment ${paymentIdByParams}", exception)
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
        } catch (BusinessException businessException) {
            log.error("PaymentController.update >> Não foi possível atualizar o Payment ${paymentIdByParams}", exception)
            params.errorMessage = exception.message
            redirect(action: "edit", params: params)
        } catch (Exception exception) {
            log.error("PaymentController.update >> Não foi possível atualizar a Payment ${paymentIdByParams}", exception)
            params.errorMessage = "Não foi possível editar a cobrança"
            redirect(action: "edit", params: params)
        }
    }

    def delete() {
        Long paymentIdByParams = params.getLong("id")
        try {
            paymentService.delete(paymentIdByParams, customer.id)
            redirect(action: "index")
        } catch (BusinessException businessException) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "show", params: params)
        } catch (Exception exception) {
            log.error("PaymentController.delete >> Não foi possível deletar a Payment ${paymentIdByParams}", exception)
            params.errorMessage = "Não foi possível apagar a cobrança"
            redirect(action: "show", params: params)
        }
    }

    def restore() {
        try {
            paymentService.restore(params.getLong("id"), customer.id)
            redirect(action: "list")
        } catch(Exception exception) {
            log.error("PaymentController.restore >> Não foi possível restaurar a Payment ${paymentIdByParams}", exception)
            params.errorMessage = "Não foi possível restaurar a cobrança"
            redirect(action: "list", params: params)
        }
    }

    def list() {
        Boolean deletedOnly = false
        try {
            List<Payment> paymentList = PaymentRepository.listByCustomer(customer.id, deletedOnly)

            deletedOnly = true
            List<Payment> deletedPaymentList = PaymentRepository.listByCustomer(customer.id, deletedOnly)
            
            return [paymentList: paymentList, deletedPaymentList: deletedPaymentList]
        } catch(Exception exception) {
            log.error("PaymentController.list >> Não foi possível listar as Payments ${paymentIdByParams}", exception)
            params.errorMessage = "Não foi possível listar as cobranças"
            redirect(action: "index", params: params)
        }
    }
}
