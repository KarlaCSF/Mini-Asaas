package com.mini.asaas.payer

import com.mini.asaas.payer.Payer
import com.mini.asaas.Address
import com.mini.asaas.Customer
import com.mini.asaas.AddressService
import com.mini.asaas.dto.payer.PayerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import javax.transaction.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class PayerService {
    
    AddressService addressService

    public Payer save(PayerDTO payerDTO, Long customerId) {
        Payer validatedPayer = validateSave(payerDTO, new Payer())

        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao salvar conta", validatedPayer.errors)

        validatedPayer.name = payerDTO.name
        validatedPayer.email = payerDTO.email
        validatedPayer.cpfCnpj = payerDTO.cpfCnpj
        validatedPayer.customer = Customer.get(customerId)
        validatedPayer.personType = CpfCnpjUtils.getPersonType(validatedPayer.cpfCnpj)
        
        validatedPayer.address = addressService.save(payerDTO.addressDTO)
        
        return validatedPayer.save(failOnError: true)
    }

    public Payer update(PayerDTO payerDTO, Long payerId) {
        Payer payer = Payer.where{
            id == payerId 
            && deleted == false
        }.first()

        Payer validatedPayer = validateSave(payerDTO, payer)

        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao editar pagador", validatedPayer.errors)
        
        validatedPayer.name = payerDTO.name
        validatedPayer.email = payerDTO.email
        validatedPayer.cpfCnpj = payerDTO.cpfCnpj
        validatedPayer.personType = CpfCnpjUtils.getPersonType(validatedPayer.cpfCnpj)
        validatedPayer.address = addressService.update(payerDTO.addressDTO, validatedPayer.address.id)
        
        return validatedPayer.save(failOnError: true)
    }

    private Payer validateSave(PayerDTO payerDTO, Payer payer) {
        if (!CpfCnpjUtils.validate(payerDTO.cpfCnpj)) {
            payer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return payer
    }

    public void delete(Long payerId) {
        Payer payer = Payer.where{
            id == payerId 
            && deleted == false
        }.first()
    
        payer.deleted = true 

        payer.save(failOnError: true)
        
    }
}
