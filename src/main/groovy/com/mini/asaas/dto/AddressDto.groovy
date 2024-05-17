package com.mini.asaas.dto

class AddressDto {
  
    String cep

    String city

    String state

    String district

    String street

    String number

    String complement
            
    AddressDto(Map params){
            this.cep = params.cep
            this.city = params.city
            this.state = params.state
            this.district = params.district
            this.street = params.street
            this.number = params.number
            this.complement = params.complement
    } 
}