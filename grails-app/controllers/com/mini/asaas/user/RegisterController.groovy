package com.mini.asaas.user

import com.mini.asaas.user.UserService
import com.mini.asaas.dto.user.UserDTO
import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class RegisterController {

    UserService userService

    def index() {
        render(view: 'register')
    }

    def register() {
        try {
            UserDTO userDTO = new UserDTO(params)
            Role adminRole = Role.findByAuthority('ROLE_ADMIN')
            userService.save(userDTO, adminRole)
            redirect(controller: 'customer')
        } catch (Exception exception) {
            log.error("RegisterController.register >> Não foi possível registrar o usuário", exception)
            params.errorMessage = "Não foi possível registrar o usuário"
            redirect(action: 'index')
        }
    }
}
