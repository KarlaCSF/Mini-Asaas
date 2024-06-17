package com.mini.asaas.dto.payer

import com.mini.asaas.dto.AddressDTO
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class PayerDTO {
  
    String name
    
    String email
    
    String cpfCnpj

    String phone

    AddressDTO addressDTO

    public PayerDTO(Map params){
        this.name = params.name
        this.email = params.email
        this.cpfCnpj = params.cpfCnpj
        this.phone = params.phone
        this.addressDTO = new AddressDTO(params)
    } 
}
