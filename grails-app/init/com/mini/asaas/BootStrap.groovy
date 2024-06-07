package com.mini.asaas

import com.mini.asaas.User
import com.mini.asaas.Role
import com.mini.asaas.UserRole

import grails.gorm.transactions.Transactional

class BootStrap {
def init = {
        addTestUser()
    }

    @Transactional
    void addTestUser() {
        def adminRole
        if (!Role.findByAuthority('ROLE_ADMIN')) {
            adminRole = new Role(authority: 'ROLE_ADMIN')
            adminRole.save(failOnError: true)
        }

        if (!Role.findByAuthority('ROLE_SELLER')) {
            new Role(authority: 'ROLE_SELLER').save(failOnError: true)
        }

        if(!User.findByUsername('me')) {
            def testUser = new User(username: 'me', password: 'password')
            testUser.save(failOnError: true)
            UserRole.create(testUser, adminRole, true)
        }

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() >= 1
        assert Role.count() >= 1
        assert UserRole.count() == 1
    }
    
    def destroy = {
    }
}
