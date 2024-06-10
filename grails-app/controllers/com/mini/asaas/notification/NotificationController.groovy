package com.mini.asaas.notification

import com.mini.asaas.customer.Customer
import com.mini.asaas.exception.BusinessException
import com.mini.asaas.repositories.NotificationRepository

class NotificationController {

    Customer customer = Customer.get(1) // todo: fix customer Id in 1 while don't have authentication

    NotificationService notificationService

    def index() {
        List<Notification> notificationList = NotificationRepository.listAllByCustomer(customer.id)
        return [view: "index", notificationList: notificationList]
    }

    def access() {
        try {
            Long notificationIdByParams = params.getLong("id")
            Notification notification = NotificationRepository.findByIdAndCustomerId(notificationIdByParams, customer.id)
            notificationService.readNotificationByCustomer(notification.id, notification.customer.id)
            redirect(url: notification.actionLink)
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "index", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível acessar o link da notificação"
            redirect(action: "index", params: params)
        }
    }

    def delete() {
        try {
            Long notificationIdByParams = params.getLong("id")
            notificationService.delete(notificationIdByParams, customer.id)
            redirect(action: "index")
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "index", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível apagar a notificação"
            redirect(action: "index", params: params)
        }
    }

    def read() {
        try {
            Long notificationIdByParams = params.getLong("id")
            notificationService.readNotificationByCustomer(notificationIdByParams, customer.id)
            redirect(action: "index")
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "index", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível marcar como lida a notificação"
            redirect(action: "index", params: params)
        }
    }

    def unread() {
        try {
            Long notificationIdByParams = params.getLong("id")
            notificationService.unreadNotificationByCustomer(notificationIdByParams, customer.id)
            redirect(action: "index")
        } catch (BusinessException exception) {
            log.error(exception.message, exception)
            params.errorMessage = exception.message
            redirect(action: "index", params: params)
        } catch (Exception exception) {
            log.error(exception.message, exception)
            params.errorMessage = "Não foi possível marcar como não lida a notificação"
            redirect(action: "index", params: params)
        }
    }
}