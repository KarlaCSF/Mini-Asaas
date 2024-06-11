package com.mini.asaas.repositories

import com.mini.asaas.user.User
import com.mini.asaas.repositories.Repository

import grails.gorm.DetachedCriteria

class UserRepository implements Repository {

    public static DetachedCriteria<User> query(Map search) {
        return User.where(defaultQuery(search))
    }
    
    public static List<User> listByCustomer(Long customerId) {
        return UserRepository.query([customerId: customerId]).list()
    }
}
