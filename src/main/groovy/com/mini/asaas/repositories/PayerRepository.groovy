package com.mini.asaas.repositories

import com.mini.asaas.payer.Payer
import grails.gorm.DetachedCriteria

class PayerRepository implements Repository {

    public static DetachedCriteria<Payer> query(Map search) {
        DetachedCriteria<Payer> query = Payer.where(defaultQuery(search))

        query = query.where {
            if (search.containsKey("customerId")) {
                customer {
                    eq("id", search.customerId)
                }
            }
        }

        return query
    }

    public static Payer findByIdAndCustomerId(Long payerId, Long customerId, Boolean deletedOnly) {
        return PayerRepository.query([id: payerId, customerId: customerId, deletedOnly: deletedOnly]).get()
    }

    public static List<Payer> listByCustomer(Long customerId, Boolean deletedOnly) {
        return PayerRepository.query([customerId: customerId, deletedOnly: deletedOnly]).list()
    }

    public static int countByCustomerId(Long customerId, boolean deletedOnly = false) {
        return query([column: "id", customerId: customerId, deletedOnly: deletedOnly]).list().size()
    }

    private static List<String> allowedFilters() {
        return [
                "customerId"
        ]
    }
}
