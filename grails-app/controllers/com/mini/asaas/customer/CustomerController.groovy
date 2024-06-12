package com.mini.asaas.customer

import com.mini.asaas.address.AddressService
import com.mini.asaas.dto.customer.CustomerDTO
import grails.compiler.GrailsCompileStatic
import grails.validation.ValidationException
import grails.plugin.springsecurity.annotation.Secured
import com.mini.asaas.user.User
import com.mini.asaas.user.Role
import com.mini.asaas.repositories.UserRepository
import com.mini.asaas.repositories.RoleRepository
import com.mini.asaas.user.UserService
import com.mini.asaas.dto.user.UserDTO

@GrailsCompileStatic
@Secured('ROLE_ADMIN')
class CustomerController {

    CustomerService customerService

    AddressService addressService

    UserService userService

    def index() {}

    def save() {
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = customerService.save(customerDTO)
            redirect(action: "show")
        } catch (ValidationException exception) {
            log.error("CustomerController.save >> Não foi possível salvar o Customer", exception)
            params.errorMessage = "Não foi possível salvar o cliente"
            redirect(view: "index", params: params)
        }
    }

    def show() {
        try {
            Customer customer = userService.getCustomerByUser()
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.show >> Não foi possível buscar o Customer", exception)
            render("Não foi possível buscar o Cliente")
        }
    }

    def edit() {
        try {
            Customer customer = userService.getCustomerByUser()
            return [customer: customer]
        } catch (Exception exception) {
            log.error("CustomerController.edit >> Não foi possível buscar o Customer", exception)
            params.errorMessage = "Não foi possível buscar o cliente"
            redirect(view: "index", params: params)
        }
    }

    def update() {
        try {
            CustomerDTO customerDTO = new CustomerDTO(params)
            Customer customer = userService.getCustomerByUser()
            customerService.update(customerDTO, customer)
            redirect(action: "show")
        } catch (ValidationException exception) {
            log.error("CustomerController.update >> Não foi possível atualizar o Customer", exception)
            params.errorMessage = "Não foi possível editar o cliente"
            redirect(action: "edit", params: params)
        }
    }

    def users() {
        try {
            Customer customer = userService.getCustomerByUser()
            List<User> userList = UserRepository.listByCustomer(customer.id)
            List<Role> roleList = RoleRepository.listAll()
            return [userList: userList, roleList: roleList]
        } catch (Exception exception) {
            log.error("CustomerController.users >> Não foi possível listar os Users", exception)
            params.errorMessage = "Não foi possível listar os usuários"
            redirect(view: "edit", params: params)
        }
    }

    def addUser() {
        try {
            UserDTO userDTO = new UserDTO(params)
            Role role = Role.findByAuthority(params.role)
            String randomPassword = new Random()
            userService.save(userDTO.username, randomPassword, role)
            redirect(action: 'users')
        } catch (Exception exception) {
            log.error("RegisterController.register >> Não foi possível registrar o usuário", exception)
            params.errorMessage = "Não foi possível registrar o usuário"
            redirect(action: 'edit', params: params)
        }
    }
}
