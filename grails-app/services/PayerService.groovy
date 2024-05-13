package com.mini.asaas

import javax.transaction.Transactional
import com.mini.asaas.Payer

@Transactional
class PayerService {
    public Payer save(String name, String email, String cpfCnpj, String address, String password) {
        Payer payer = new Payer();
        payer.name = name;
        payer.email = email;
        payer.cpfCnpj = cpfCnpj;
        payer.address = address;
        payer.password =password;
        payer.save(failOnError: true);
        return payer;
    }
}
