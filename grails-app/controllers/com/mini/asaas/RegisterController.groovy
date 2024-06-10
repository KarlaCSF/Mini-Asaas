package com.mini.asaas

import com.mini.asaas.UserService
import grails.gorm.transactions.Transactional

class RegisterController {

    UserService userService

    def index() {
        render(view: 'register')
    }

    def register() {
        try {
            Role adminRole = Role.findByAuthority('ROLE_ADMIN')
            userService.save(params.email, params.password, adminRole)
            redirect(controller: 'customer')
        } catch (Exception exception) {
            log.error("RegisterController.register >> Não foi possível registrar o usuário", exception)
            params.errorMessage = "Não foi possível registrar o usuário"
            redirect(action: 'index')
        }
    }
}
