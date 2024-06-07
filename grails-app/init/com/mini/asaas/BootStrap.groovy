package com.mini.asaas

import com.mini.asaas.User
import com.mini.asaas.Role
import com.mini.asaas.UserRole

class BootStrap {
    def init = {
        if (!Role.findByAuthority('ROLE_ADMIN')) {
            new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        }

        if (!Role.findByAuthority('ROLE_SELLER')) {
            new Role(authority: 'ROLE_SELLER').save(failOnError: true)
        }
    }
    
    def destroy = {
    }
}
