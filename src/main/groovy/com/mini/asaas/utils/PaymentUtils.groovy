package com.mini.asaas.utils

import com.mini.asaas.payment.Payment

class PaymentUtils {

    static Map<String, Object> mapPaymentDetails(Payment payment, String paymentLink = null) {
        def details = [
                customerCity    : payment.customer.address.city,
                customerCpfCnpj : payment.customer.cpfCnpj,
                customerEmail   : payment.customer.email,
                customerName    : payment.customer.name,
                customerState   : payment.customer.address.state,
                payerName       : payment.payer.name,
                paymentDueDate  : payment.dueDate,
                paymentValue    : payment.value
        ]
        if (paymentLink) {
            details.paymentLink = paymentLink
        }
        return details
    }
}

