package com.mini.asaas.dto.user

import grails.compiler.GrailsCompileStatic
import com.mini.asaas.customer.Customer

@GrailsCompileStatic
class UserDTO {
  
    String username
    
    String password

    Customer customer

    public UserDTO(Map params){
        this.username = params.email
        this.password = params.password
        this.customer = params.customer as Customer
    } 
}
