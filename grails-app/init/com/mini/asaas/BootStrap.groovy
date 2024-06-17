package com.mini.asaas

import com.mini.asaas.user.User
import com.mini.asaas.user.Role
import com.mini.asaas.user.UserRole

import grails.gorm.transactions.Transactional

class BootStrap {

    def init = {
        addTestUser()
    }

    @Transactional
    void addTestUser() {
        if (!Role.findByAuthority('ROLE_SELLER')) {
            new Role(authority: 'ROLE_SELLER').save(failOnError: true)
        }

        def adminRole
        if (!Role.findByAuthority('ROLE_ADMIN')) {
            adminRole = new Role(authority: 'ROLE_ADMIN')
            adminRole.save(failOnError: true)
        }

        if (!User.findByUsername('admin')) {
            def testUser = new User(username: 'admin', password: 'admin')
            testUser.save(failOnError: true)
            UserRole.create(testUser, adminRole, true)
        }

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }
    
    def destroy = {
    }
}
