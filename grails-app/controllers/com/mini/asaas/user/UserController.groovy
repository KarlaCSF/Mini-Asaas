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
        try {
            Long customerId = userService.getCurrentCustomerIdForLoggedUser()
            List<User> userList = UserRepository.listByCustomer(customerId)
            List<Role> roleList = RoleRepository.listAll()
            return [userList: userList, roleList: roleList]
        } catch (Exception exception) {
            log.error("UserController.users >> Não foi possível listar os Users", exception)
            params.errorMessage = "Não foi possível listar os usuários"
            render("Não foi possível listar os usuários")
        }
    }

    def add() {
        try {
            Random randomPassword = new Random()
            int maxLenght = 999999
            params.password = randomPassword.nextInt(maxLenght)

            Customer customer = userService.getCurrentCustomerForLoggedUser()
            params.customer = customer
 
            params.role = Role.findByAuthority(params.role)
            UserDTO userDTO = new UserDTO(params)

            User user = userService.create(userDTO)

            emailService.notifyOnNewUser(user, params.password.toString())

            redirect(action: 'users')
        } catch (Exception exception) {
            log.error("UserController.add >> Não foi possível adicionar o User", exception)
            params.errorMessage = "Não foi possível adicionar o usuário"
            redirect(action: 'users', params: params)
        }
    }

    def edit() {
        try {
            User user = userService.getCurrentUser()
            return [user: user]
        } catch (Exception exception) {
            log.error("UserController.edit >> Não foi possível buscar o User", exception)
            params.errorMessage = "Não foi possível buscar o usuário"
            redirect(action: "users", params: params)
        }
    }

    def update() {
        try {
            User user = userService.getCurrentUser()
            UserDTO userDTO = new UserDTO(params)
            userService.update(userDTO, user)
            redirect(action: 'users')
        } catch (Exception exception) {
            log.error("UserController.update >> Não foi possível atualizar o usuário", exception)
            params.errorMessage = "Não foi possível atualizar o usuário"
            redirect(action: 'edit', params: params)
        }
    }
}
