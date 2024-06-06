package com.mini.asaas.enums.email

enum EmailTemplateType {
    PAYMENT_CREATE("/email/payment/CreatePayment"),
    PAYMENT_DELETE("/email/payment/DeletePayment"),
    PAYMENT_UPDATE("/email/payment/UpdatePayment")

    final String viewPath

    EmailTemplateType(String viewPath) {
        this.viewPath = viewPath
    }

    String getViewPath() {
        return viewPath
    }
}
