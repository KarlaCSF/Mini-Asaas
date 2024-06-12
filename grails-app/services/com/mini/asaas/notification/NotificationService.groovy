package com.mini.asaas.notification

import com.mini.asaas.customer.Customer
import com.mini.asaas.customer.CustomerService
import com.mini.asaas.repositories.NotificationRepository
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@Transactional
@GrailsCompileStatic
class NotificationService {

    CustomerService customerService

    public void create(String title, String description, String actionLink, Customer customer) {
        Notification notification = new Notification()
        notification.customer = customer
        notification.description = description
        notification.title = title
        notification.actionLink = actionLink
        notification.save(failOnError: true)
    }

    public void readNotificationByCustomer(Long notificationId, Long customerId) {
        Notification notification = NotificationRepository.findByIdAndCustomerId(notificationId, customerId)
        modifyReadStatus(notification, true)
    }

    public void unreadNotificationByCustomer(Long notificationId, Long customerId) {
        Notification notification = NotificationRepository.findByIdAndCustomerId(notificationId, customerId)
        modifyReadStatus(notification, false)
    }

    public void delete(Long notificationId, Long customerId) {
        Notification notification = NotificationRepository.findByIdAndCustomerId(notificationId, customerId)
        notification.deleted = true
        notification.save()
    }

    private static void modifyReadStatus(Notification notification, Boolean notificationReadStatus) {
        notification.isRead = notificationReadStatus
        notification.save()
    }
}
