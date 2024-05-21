package com.mini.asaas

import javax.transaction.Transactional
import com.mini.asaas.Payer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import com.mini.asaas.Customer
import com.mini.asaas.dto.PayerDTO

@Transactional
class PayerService {
    AddressService addressService

    public Payer save(PayerDTO payerDTO, Long customerId) {
        Payer payer = new Payer()

        payer.name = payerDTO.name
        payer.email = payerDTO.email
        payer.cpfCnpj = payerDTO.cpfCnpj
        payer.customer = Customer.get(customerId)
        payer.personType = PersonType.NATURAL //TODO: set per default natural while don't have verification what is the type// 
        
        payer.address = addressService.save(payerDTO.addressDTO)
        
        return payer.save(failOnError: true)
    }
}
