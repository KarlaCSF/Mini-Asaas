package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.Address
import com.mini.asaas.Customer
import com.mini.asaas.AddressService
import com.mini.asaas.dto.PayerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import javax.transaction.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class PayerService {
    
    AddressService addressService

    public Payer save(PayerDTO payerDTO, Long customerId) {
        Payer payer = validateSave(payerDTO)

        if (payer.hasErrors()) throw new ValidationException("Erro ao salvar conta", payer.errors)

        payer.name = payerDTO.name
        payer.email = payerDTO.email
        payer.cpfCnpj = payerDTO.cpfCnpj
        payer.customer = Customer.get(customerId)
        payer.personType = CpfCnpjUtils.getPersonType(payer.cpfCnpj)
        
        payer.address = addressService.save(payerDTO.addressDTO)
        
        return payer.save(failOnError: true)
    }

    private Payer validateSave(PayerDTO payerDTO) {
        Payer payer = new Payer()
        
        if (!CpfCnpjUtils.validate(payerDTO.cpfCnpj)) {
            payer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return payer
    }
}
