package com.mini.asaas.repositories

import com.mini.asaas.user.User
import com.mini.asaas.repositories.Repository

import grails.gorm.DetachedCriteria

class UserRepository implements Repository {

    public static DetachedCriteria<User> query(Map search) {
        DetachedCriteria<User> query = User.where(defaultQuery(search))
    
        query = query.where {
            if (search.containsKey("customerId")) {
                customer{
                    eq("id", search.customerId)
                }
            }
        }        
    
        return query
    }
    
    public static List<User> listByCustomer(Long customerId) {
        println customerId
        return UserRepository.query([customerId: customerId]).list()
    }

    private static List<String> allowedFilters() {
        return [
            "customerId"
        ]
    }
}
