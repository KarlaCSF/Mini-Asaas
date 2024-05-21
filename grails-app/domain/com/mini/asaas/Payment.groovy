package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.Customer
import com.mini.asaas.enums.PaymentType
import com.mini.asaas.utils.BaseEntity

class Payment extends BaseEntity {

    Customer customer

    Payer payer 

    BigDecimal value 

    Date dueDate 

    PaymentType paymentType 
        
    static constraints = {
        customer blank:false
        payer blank:false
        value blank:false
        dueDate blank:false
        paymentType blank:false
    }
}
