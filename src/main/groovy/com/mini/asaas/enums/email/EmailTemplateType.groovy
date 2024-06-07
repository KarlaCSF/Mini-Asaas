package com.mini.asaas.enums.email

enum EmailTemplateType {
    PAYMENT_CREATE("/email/payment/CreatePayment"),
    PAYMENT_DELETE("/email/payment/DeletePayment"),
    PAYMENT_OVERDUE("/email/payment/OverduePayment"),
    PAYMENT_VERIFY("/email/payment/VerifyPayment"),
    PAYMENT_UPDATE("/email/payment/UpdatePayment")

    final String viewPath

    EmailTemplateType(String viewPath) {
        this.viewPath = viewPath
    }
}
