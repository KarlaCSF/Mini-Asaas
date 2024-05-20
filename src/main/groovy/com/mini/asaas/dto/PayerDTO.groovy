package com.mini.asaas.dto

import com.mini.asaas.dto.AddressDTO

class PayerDTO {
  
    String name
    
    String email
    
    String cpfCnpj

    Long customerId

    AddressDTO addressDTO

    PayerDTO(Map params){
            this.name = params.name
            this.email = params.email
            this.cpfCnpj = params.cpfCnpj
            this.customerId = new Long(params.customerId)
            this.addressDTO = new AddressDTO(params)     
    } 
}