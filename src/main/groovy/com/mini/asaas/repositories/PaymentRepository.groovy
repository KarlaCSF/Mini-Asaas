package com.mini.asaas.repositories

import com.mini.asaas.payment.Payment
import com.mini.asaas.enums.payment.PaymentStatus
import com.mini.asaas.repositories.Repository
import grails.gorm.DetachedCriteria

class PaymentRepository implements Repository {

    public static DetachedCriteria<Payment> query(Map search) {
        DetachedCriteria<Payment> query = Payment.where(defaultQuery(search))
    
        query = query.where {
            if (search.containsKey("customerId")) {
                customer{
                    eq("id", search.customerId)
                }
            }

            if (search.containsKey("value")) {
                eq("value", search.value)
            }

            if (search.containsKey("dueDate")) {
                eq("dueDate", search.dueDate)
            }

            if (search.containsKey("billingType")) {
                eq("billingType", search.billingType)
            }
            
            if (search.containsKey("status")) {
                eq("status", search.status)
            }
        }        
    
        return query
    }
    
    public static Payment findByIdAndCustomerId(Long paymentId, Long customerId){
        Payment payment = PaymentRepository.query([id: paymentId, customerId: customerId]).get()
        if (!payment) throw new Exception("Cobrança inexistente.")
        return payment
    }

    public static List<Payment> listByCustomer(Long customerId){
        return PaymentRepository.query([customerId: customerId]).list()
    }

    public static List<Payment> listByStatus(PaymentStatus status) {
        return PaymentRepository.query([status: status]).list()
    }

    private static List<String> allowedFilters() {
        return [
            "id",
            "value",
            "dueDate",
            "billingType",
            "status"
        ]
    }
}
