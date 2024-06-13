package com.mini.asaas.dto.user

import grails.compiler.GrailsCompileStatic
import com.mini.asaas.customer.Customer
import com.mini.asaas.user.Role

@GrailsCompileStatic
class UserDTO {
  
    String username
    
    String password

    Customer customer

    Role role

    public UserDTO(Map params){
        this.username = params.email
        this.password = params.password
        this.customer = (Customer) params.customer
        this.role = (Role) params.role
    } 
}
