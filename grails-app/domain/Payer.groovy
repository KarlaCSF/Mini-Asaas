package com.mini.asaas
import com.mini.asaas.utils.BaseEntity

class Payer extends BaseEntity {
    String name;
    String email;
    String cpfCnpj;
    String address;
    String password;
    static constraints = {
    }
}
