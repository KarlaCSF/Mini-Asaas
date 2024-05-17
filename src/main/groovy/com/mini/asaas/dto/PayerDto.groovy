package com.mini.asaas.dto

import com.mini.asaas.dto.AddressDto

class PayerDto {
  
    String name
    
    String email
    
    String cpfCnpj

    Long customerId

    AddressDto addressDto

    PayerDto(Map params){
            this.name = params.name
            this.email = params.email
            this.cpfCnpj = params.cpfCnpj
            this.customerId = new Long(params.customerId)
            this.addressDto = new AddressDto(params)     
    } 
}