package com.mini.asaas

import javax.transaction.Transactional
import com.mini.asaas.Payer
import com.mini.asaas.Address

@Transactional
class PayerService {
    public Payer save(String name, String email, String cpfCnpj, Address address) {
        Payer payer = new Payer()
        payer.name = name
        payer.email = email
        payer.cpfCnpj = cpfCnpj
        payer.address = address
        payer.personType = PersonType.NATURAL //TODO: set per default natural while don't have verification what is the type// 

        return payer.save(failOnError: true)
    }
}
