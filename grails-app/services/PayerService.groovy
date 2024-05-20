package com.mini.asaas

import javax.transaction.Transactional
import com.mini.asaas.Payer
import com.mini.asaas.Address
import com.mini.asaas.AddressService
import com.mini.asaas.Customer
import com.mini.asaas.dto.PayerDto

@Transactional
class PayerService {
    AddressService addressService

    public Payer save(PayerDto payerDto) {
        Payer payer = new Payer()

        payer.name = payerDto.name
        payer.email = payerDto.email
        payer.cpfCnpj = payerDto.cpfCnpj
        payer.customer = Customer.get(payerDto.customerId)
        payer.personType = payer.cpfCnpj.size() > 11 ? PersonType.LEGAL : PersonType.NATURAL

        payer.address = addressService.save(payerDto.addressDto)
        
        return payer.save(failOnError: true)
    }
}
