package com.mini.asaas.notification

import com.mini.asaas.base.BaseEntity
import com.mini.asaas.customer.Customer

class Notification extends BaseEntity {

    Customer customer

    String description

    Boolean isRead = false

    String title

    static constraints = {
        customer blank: false
        description blank: true
        isRead blank: false
        title blank: false
    }
}
