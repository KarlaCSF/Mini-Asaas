package com.mini.asaas.user

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import com.mini.asaas.customer.Customer
import com.mini.asaas.user.User
import com.mini.asaas.user.Role
import com.mini.asaas.repositories.UserRepository
import com.mini.asaas.repositories.RoleRepository
import com.mini.asaas.user.UserService
import com.mini.asaas.dto.user.UserDTO
import com.mini.asaas.email.EmailService

@Secured('ROLE_ADMIN')
class UserController {

    UserService UserService

    EmailService emailService

    def index() {
        redirect(action: "users")
    }

    def users() {
        Long customerId
        try {
            customerId = userService.getCurrentCustomerIdForLoggedUser()
            List<User> userList = UserRepository.listByCustomer(customerId)
            List<Role> roleList = RoleRepository.listAll()
            return [userList: userList, roleList: roleList]
        } catch (Exception exception) {
            log.error("UserController.users >> Não foi possível listar os Users", exception)
            flash.message = "Não foi possível listar os usuários."
            flash.type = "error"
            render("Não foi possível listar os usuários")
        }
    }

    def add() {
        Customer customer
        try {
            Random randomPassword = new Random()
            int maxLength = 999999
            params.password = randomPassword.nextInt(maxLength)

            customer = userService.getCurrentCustomerForLoggedUser()
            params.customer = customer
 
            params.role = Role.findByAuthority(params.role)
            UserDTO userDTO = new UserDTO(params)

            User user = userService.create(userDTO)

            emailService.notifyOnNewUser(user, params.password.toString())

            flash.message = "Usuário adicionado com sucesso!"
            flash.type = "success"
            redirect(action: 'users')
        } catch (Exception exception) {
            log.error("UserController.add >> Não foi possível adicionar o User no Customer ${customer.id}", exception)
            flash.message = "Não foi possível adicionar o usuário."
            flash.type = "error"
            redirect(action: 'users')
        }
    }

    def edit() {
        User user
        try {
            user = userService.getCurrentUser()
            return [user: user]
        } catch (Exception exception) {
            log.error("UserController.edit >> Não foi possível buscar o User ${user.id}", exception)
            flash.message = "Não foi possível buscar o usuário."
            flash.type = "error"
            redirect(action: "users")
        }
    }

    def update() {
        User user
        try {
            user = userService.getCurrentUser()
            UserDTO userDTO = new UserDTO(params)
            userService.update(userDTO, user)

            flash.message = "Senha alterada com sucesso!"
            flash.type = "success"
            redirect(action: 'edit')
        } catch (Exception exception) {
            log.error("UserController.update >> Não foi possível atualizar o User ${user.id}", exception)
            flash.message = "Não foi possível alterar a senha."
            flash.type = "error"
            redirect(action: 'edit')
        }
    }
}
