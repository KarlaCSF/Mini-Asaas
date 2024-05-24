package com.mini.asaas.dto

import com.mini.asaas.dto.AddressDTO
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CustomerDTO {
  
    String name
    
    String email
    
    String cpfCnpj

    AddressDTO addressDTO

    CustomerDTO(Map params){
            this.name = params.name
            this.email = params.email
            this.cpfCnpj = params.cpfCnpj
            this.addressDTO = new AddressDTO(params)
    } 
}