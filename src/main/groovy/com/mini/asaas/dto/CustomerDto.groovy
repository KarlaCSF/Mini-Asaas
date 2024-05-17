package com.mini.asaas.dto

import com.mini.asaas.dto.AddressDto

class CustomerDto {
  
    String name
    
    String email
    
    String cpfCnpj

    AddressDto addressDto

    CustomerDto(Map params){
            this.name = params.name
            this.email = params.email
            this.cpfCnpj = params.cpfCnpj
            this.addressDto = new AddressDto(params)
    } 
}