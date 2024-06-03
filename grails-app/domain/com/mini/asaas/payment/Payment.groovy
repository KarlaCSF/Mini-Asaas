package com.mini.asaas.payment

import com.mini.asaas.payer.Payer
import com.mini.asaas.Customer
import com.mini.asaas.enums.payment.BillingType
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.base.BaseEntity
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Payment extends BaseEntity {

    Customer customer

    Payer payer 

    BigDecimal value 

    Date dueDate 

    BillingType billingType 

    PaymentStatus status

    static constraints = {
        customer blank:false
        payer blank:false
        value blank:false
        dueDate blank:false
        billingType blank:false
        status blank:false
    }   

    public Boolean canEdit(){
        if (this.status.isPaid()) return false
        return true
    }
}
