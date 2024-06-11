package com.mini.asaas.user

import grails.gorm.transactions.Transactional
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class UserService {
    User save(String username, String password, Role role) {
        def user = new User(username: username, password: password)
        user.save(failOnError: true)
    
        UserRole.create(user, role)

        return user
    }
}
