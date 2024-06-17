package com.mini.asaas.enums.email

enum EmailTemplateType {
    PAYMENT_TO_PAYER("/email/payment/PaymentToPayer"),
    NEW_USER("/email/NewUser"),

    final String viewPath

    EmailTemplateType(String viewPath) {
        this.viewPath = viewPath
    }
}
