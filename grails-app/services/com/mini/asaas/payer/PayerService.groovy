package com.mini.asaas.payer

import com.mini.asaas.address.AddressService
import com.mini.asaas.customer.CustomerService
import com.mini.asaas.dto.payer.PayerDTO
import com.mini.asaas.repositories.CustomerRepository
import com.mini.asaas.repositories.PayerRepository
import com.mini.asaas.utils.CpfCnpjUtils
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional
import grails.validation.ValidationException

@GrailsCompileStatic
@Transactional
class PayerService {

    AddressService addressService

    CustomerService customerService

    public Payer save(PayerDTO payerDTO, Long customerId) {
        Payer validatedPayer = validateSave(payerDTO, new Payer())

        if (validatedPayer.hasErrors()) throw new ValidationException("Erro ao salvar conta", validatedPayer.errors)

        validatedPayer.name = payerDTO.name
        validatedPayer.email = payerDTO.email
        validatedPayer.cpfCnpj = payerDTO.cpfCnpj
        validatedPayer.customer = CustomerRepository.findById(customerId)
        validatedPayer.personType = CpfCnpjUtils.getPersonType(validatedPayer.cpfCnpj)

        validatedPayer.address = addressService.save(payerDTO.addressDTO)

        return validatedPayer.save(failOnError: true)
    }

    public Payer update(PayerDTO payerDTO, Long payerId, Long customerId) {
        Payer payer = PayerRepository.findByIdAndCustomerId(payerId, customerId, false)

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

    public void delete(Long payerId, Long customerId) {
        Payer payer = PayerRepository.findByIdAndCustomerId(payerId, customerId, false)
        payer.deleted = true
        payer.save(failOnError: true)
    }

    public Payer restore(Long payerId, Long customerId) {
        Payer payer = PayerRepository.findByIdAndCustomerId(payerId, customerId, true)
        payer.deleted = false
        payer.save(failOnError: true)
    }
}
