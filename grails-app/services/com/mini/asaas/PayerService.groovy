package com.mini.asaas

import com.mini.asaas.Payer
import com.mini.asaas.Address
import com.mini.asaas.Customer
import com.mini.asaas.AddressService
import com.mini.asaas.dto.payer.CreatePayerDTO
import com.mini.asaas.dto.payer.UpdatePayerDTO
import com.mini.asaas.utils.CpfCnpjUtils

import javax.transaction.Transactional
import grails.validation.ValidationException
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
@Transactional
class PayerService {
    
    AddressService addressService

    public Payer save(CreatePayerDTO createPayerDTO, Long customerId) {
        Payer payer = validateSave(createPayerDTO)

        if (payer.hasErrors()) throw new ValidationException("Erro ao salvar conta", payer.errors)

        payer.name = createPayerDTO.name
        payer.email = createPayerDTO.email
        payer.cpfCnpj = createPayerDTO.cpfCnpj
        payer.customer = Customer.get(customerId)
        payer.personType = CpfCnpjUtils.getPersonType(payer.cpfCnpj)
        
        payer.address = addressService.save(createPayerDTO.addressDTO)
        
        return payer.save(failOnError: true)
    }

    public Payer update(UpdatePayerDTO updatePayerDTO, Long payerId) {
        Payer payer = Payer.where{
            id == payerId 
            && deleted == false
        }.first()

        Payer validatedPayer = validateUpdate(updatePayerDTO, payer)

        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao editar pagador", validatedPayer.errors)
        
        validatedPayer.name = updatePayerDTO.name
        validatedPayer.email = updatePayerDTO.email
        validatedPayer.cpfCnpj = updatePayerDTO.cpfCnpj
        validatedPayer.personType = CpfCnpjUtils.getPersonType(validatedPayer.cpfCnpj)
        validatedPayer.address = addressService.update(updatePayerDTO.addressDTO, validatedPayer.address.id)
        
        return validatedPayer.save(failOnError: true)
    }

    private Payer validateSave(CreatePayerDTO createPayerDTO) {
        Payer payer = new Payer()
        
        if (!CpfCnpjUtils.validate(createPayerDTO.cpfCnpj)) {
            payer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }
        
        return payer
    }

    private Payer validateUpdate(UpdatePayerDTO updatePayerDTO, Payer payer) {
        if (!CpfCnpjUtils.validate(updatePayerDTO.cpfCnpj)) {
            payer.errors.reject("cpfCnpj", null, "CPF ou CNPJ inválido.")
        }

        return payer
    }
}
