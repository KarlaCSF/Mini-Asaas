package com.mini.asaas

import com.mini.asaas.Customer
import com.mini.asaas.Address
import grails.gorm.transactions.Transactional

@Transactional
class CustomerService {
   public Customer save(String name, String email, String cpfCnpj, Address address) {
       Customer customer = new Customer()
       customer.name = name
       customer.email = email
       customer.cpfCnpj = cpfCnpj
       customer.address = address
       customer.personType = PersonType.NATURAL
       
       return customer.save(failOnError: true)
   }
}
