package com.mini.asaas.user

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic
import grails.plugin.springsecurity.SpringSecurityService
import com.mini.asaas.customer.Customer
import com.mini.asaas.dto.user.UserDTO

@GrailsCompileStatic
@Transactional
class UserService {
    SpringSecurityService springSecurityService

    public User save(UserDTO userDTO, Role role) {
        def user = new User(username: userDTO.username, password: userDTO.password)
        user.customer = userDTO.customer

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