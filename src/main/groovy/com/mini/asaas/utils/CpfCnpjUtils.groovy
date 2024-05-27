package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic
import com.mini.asaas.enums.PersonType

@GrailsCompileStatic
class CpfCnpjUtils {
    static final int maxLengthCpf = 11
    static final int maxLengthCnpj = 14

    public static boolean validate(String cpfCnpj) {
        
        cpfCnpj = cpfCnpj.replaceAll("[^\\d]", "")

        if (cpfCnpj.length() == maxLengthCpf) return isValidCPF(cpfCnpj)
        if (cpfCnpj.length() == maxLengthCnpj) return isValidCNPJ(cpfCnpj)

        return false
    }

    public static boolean isValidCPF(String cpf) {

        if (cpf ==~ /(\d)\1{10}/) {
            return false
        }

        def sum = 0
        def weight = 10
        
        for (i in 0..<9) {
            sum += (cpf[i].toInteger() * weight)
            weight--
        }
        
        def firstDigit = (11 - (sum % 11)) % 10
        
        sum = 0
        weight = 11
        
        for (i in 0..<10) {
            sum += (cpf[i].toInteger() * weight)
            weight--
        }
        
        def secondDigit = (11 - (sum % 11)) % 10
        
        return cpf[9].toInteger() == firstDigit && cpf[10].toInteger() == secondDigit
    }

    public static boolean isValidCNPJ(String cnpj) {

        if (cnpj ==~ /(\d)\1{13}/) {
            return false
        }
        
        def sum = 0
        def weight = [5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
        
        for (i in 0..<12) {
            sum += (cnpj[i].toInteger() * weight[i])
        }
        
        def firstDigit = sum % 11
        firstDigit = firstDigit < 2 ? 0 : 11 - firstDigit
        
        sum = 0
        weight = [6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2]
        
        for (i in 0..<13) {
            sum += (cnpj[i].toInteger() * weight[i])
        }
        
        def secondDigit = (11 - (sum % 11)) % 10
        secondDigit = secondDigit < 2 ? 0 : 11 - secondDigit
        
        return cnpj[12].toInteger() == firstDigit && cnpj[13].toInteger() == secondDigit
    }

    public static PersonType getPersonType(String cpfCnpj) {
        return cpfCnpj.size() > maxLengthCpf ? PersonType.LEGAL : PersonType.NATURAL
    }
}