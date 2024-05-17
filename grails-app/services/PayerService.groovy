package com.mini.asaas

import javax.transaction.Transactional
import com.mini.asaas.Payer
import com.mini.asaas.Address
import com.mini.asaas.Customer

@Transactional
class PayerService {
    public Payer save(String name, String email, BigInteger customerId, String cpfCnpj, Address address) {
        Payer payer = new Payer()
        payer.name = name
        payer.email = email
        payer.cpfCnpj = cpfCnpj
        payer.customer = Customer.get(customerId);
        payer.address = address
        payer.personType = PersonType.NATURAL //TODO: set per default natural while don't have verification what is the type// 

        return payer.save(failOnError: true)
    }
}
