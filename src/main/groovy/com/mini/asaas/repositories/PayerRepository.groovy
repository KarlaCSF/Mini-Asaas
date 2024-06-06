package com.mini.asaas.repositories

import com.mini.asaas.payer.Payer
import com.mini.asaas.repositories.Repository
import grails.gorm.DetachedCriteria

class PayerRepository implements Repository {

    public static DetachedCriteria<Payer> query(Map search) {
        DetachedCriteria<Payer> query = Payer.where(defaultQuery(search))
    
        query = query.where {
            if (search.containsKey("customerId")) {
                customer{
                    eq("id", search.customerId)
                }
            }
        }        
    
        return query
    }
    
    public static Payer findByIdAndCustomerId(Long payerId, Long customerId, Boolean deletedOnly) {
        Payer payer = PayerRepository.query([id: payerId, customerId: customerId, deletedOnly: deletedOnly]).get()
        if (!payer) throw new Exception("Payer inexistente.")
        return payer
    }

    public static List<Payer> listByCustomer(Long customerId, Boolean deletedOnly) {
        return PayerRepository.query([customerId: customerId, deletedOnly: deletedOnly]).list()
    }

    private static List<String> allowedFilters() {
        return [
            "customerId"
        ]
    }
}
