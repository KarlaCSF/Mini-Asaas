package com.mini.asaas

import javax.transaction.Transactional
import com.mini.asaas.Payer

@Transactional
class PayerService {
    public Payer save(String name, String email, String cpfCnpj) {
        Payer payer = new Payer()
        payer.name = name
        payer.email = email
        payer.cpfCnpj = cpfCnpj
        payer.personType = PersonType.NATURAL // set per default natural while don't have verification what is the type// 

        return payer.save(failOnError: true)
    }
}
