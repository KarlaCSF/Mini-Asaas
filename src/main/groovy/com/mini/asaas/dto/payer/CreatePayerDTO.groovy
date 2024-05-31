package com.mini.asaas.dto.payer

import com.mini.asaas.dto.AddressDTO
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class CreatePayerDTO {
  
    String name
    
    String email
    
    String cpfCnpj

    AddressDTO addressDTO

    public CreatePayerDTO(Map params){
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.addressDTO = new AddressDTO(params)     
    } 
}