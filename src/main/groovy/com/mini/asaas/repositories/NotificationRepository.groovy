package com.mini.asaas.repositories

import com.mini.asaas.notification.Notification
import grails.gorm.DetachedCriteria

class NotificationRepository implements Repository {

    public static DetachedCriteria<Notification> query(Map search) {
        DetachedCriteria<Notification> query = Notification.where(defaultQuery(search))

        query = query.where {
            if (search.containsKey("customerId")) {
                customer {
                    eq("id", search.customerId)
                }
            }

            if (search.containsKey("description")) {
                eq("value", search.value)
            }

            if (search.containsKey("isRead")) {
                eq("dueDate", search.dueDate)
            }

            if (search.containsKey("title")) {
                eq("status", search.status)
            }
        }

        return query
    }

    public static Notification findUnreadByIdAndCustomerId(Long notificationId, Long customerId) {
        return query([id: notificationId, customerId: customerId, isRead: false]).get()
    }

    public static Notification findReadByIdAndCustomerId(Long notificationId, Long customerId) {
        return query([id: notificationId, customerId: customerId, isRead: true]).get()
    }

    public static Notification findByIdAndCustomerId(Long notificationId, Long customerId) {
        return query([id: notificationId, customerId: customerId]).get()
    }

    public static List<Notification> listUnreadByCustomer(Long customerId) {
        return query([customerId: customerId, isRead: false]).list()
    }

    public static List<Notification> listReadByCustomer(Long customerId) {
        return query([customerId: customerId, isRead: true]).list()
    }

    public static List<Notification> listAllByCustomer(Long customerId) {
        return query([customerId: customerId]).list()
    }


    private static List<String> allowedFilters() {
        return [
                "customerId",
                "description",
                "isRead",
                "title"
        ]
    }
}
