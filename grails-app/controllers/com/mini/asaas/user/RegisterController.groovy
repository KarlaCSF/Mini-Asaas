package com.mini.asaas.user

import com.mini.asaas.user.UserService
import com.mini.asaas.dto.user.UserDTO

class RegisterController {

    UserService userService

    def index() {
        render(view: 'register')
    }

    def register() {
        try {
            UserDTO userDTO = new UserDTO(params)
            Role adminRole = Role.findByAuthority('ROLE_ADMIN')
            userService.save(userDTO.username, userDTO.password, adminRole)
            redirect(controller: 'customer')
        } catch (Exception exception) {
            log.error("RegisterController.register >> Não foi possível registrar o usuário", exception)
            params.errorMessage = "Não foi possível registrar o usuário"
            redirect(action: 'index')
        }
    }
}
