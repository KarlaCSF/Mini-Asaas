package com.mini.asaas.repositories

import com.mini.asaas.customer.Customer
import com.mini.asaas.repositories.Repository
import grails.gorm.DetachedCriteria

class CustomerRepository implements Repository {

    public static DetachedCriteria<Customer> query(Map search) {
        DetachedCriteria<Customer> query = Customer.where(defaultQuery(search))    
        return query
    }
}
