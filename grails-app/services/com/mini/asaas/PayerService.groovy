package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.Address
import com.mini.asaas.Customer
import com.mini.asaas.AddressService
import com.mini.asaas.dto.PayerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import javax.transaction.Transactional

@Transactional
class PayerService {
    
    AddressService addressService

    public Payer save(PayerDTO payerDTO, Long customerId) {
        Payer payer = new Payer()

        payer.name = payerDTO.name
        payer.email = payerDTO.email
        payer.cpfCnpj = payerDTO.cpfCnpj
        payer.customer = Customer.get(customerId)
        payer.personType = CpfCnpjUtils.setPersonType(payer.cpfCnpj)
        
        payer.address = addressService.save(payerDTO.addressDTO)
        
        return payer.save(failOnError: true)
    }
}
