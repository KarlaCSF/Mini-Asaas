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
        def adminRole = new Role(authority: 'ROLE_ADMIN')
        adminRole.save()

        def testUser = new User(username: 'me', password: 'password')
        testUser.save()

        UserRole.create(testUser, adminRole, true)

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() >= 1
        assert Role.count() == 1
        assert UserRole.count() == 1
    }
    
    def destroy = {
    }
}