package com.mini.asaas.notification

import com.mini.asaas.customer.Customer
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@Transactional
@GrailsCompileStatic
class NotificationService {
    public void create(String title, String description, Customer customer) {
        Notification notification = new Notification()
        notification.customer = customer
        notification.description = description
        notification.title = title
        notification.save(failOnError: true)
    }

    public void readNotification(Notification notification) {
        notification.isRead = true
        notification.save()
    }
}
