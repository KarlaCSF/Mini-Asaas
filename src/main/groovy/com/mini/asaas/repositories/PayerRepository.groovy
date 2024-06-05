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
    
    public static Payer findByIdAndCustomerId(Long payerId, Long customerId){
        return PayerRepository.query([id: payerId, customerId: customerId]).get()
    }

    public static List<Payer> listByCustomer(Long customerId){
        return PayerRepository.query([customerId: customerId]).list()
    }

    private static List<String> allowedFilters() {
        return [
            "customerId"
        ]
    }
}
