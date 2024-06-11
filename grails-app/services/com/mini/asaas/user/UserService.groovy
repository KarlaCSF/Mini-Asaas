package com.mini.asaas.user

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import com.mini.asaas.customer.Customer

@GrailsCompileStatic
@Transactional
class UserService {
    SpringSecurityService springSecurityService

    User save(String username, String password, Role role) {
        def user = new User(username: username, password: password)
        user.save(failOnError: true)
    
        UserRole.create(user, role)

        return user
    }

    public User getCurrentUser() {
        return (User) springSecurityService.getCurrentUser()
    }

    public Customer getCustomerByUser() {
        User user = (User) springSecurityService.getCurrentUser()
        return user.customer
    }
}
