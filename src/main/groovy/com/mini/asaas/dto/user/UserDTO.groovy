package com.mini.asaas.dto.user

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class UserDTO {
  
    String username
    
    String password

    public UserDTO(Map params){
        this.username = params.email
        this.password = params.password
    } 
}
