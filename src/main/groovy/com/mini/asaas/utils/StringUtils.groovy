package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat

@GrailsCompileStatic
class StringUtils {
    public static removeNonNumeric(String text) {
        return text.replaceAll("[^\\d]", "")
    }

    public static BigDecimal parseStringToBigDecimal(String numberString) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR"));
        Number number = format.parse(numberString);
        return new BigDecimal(number.toString());
    }

    public static Date parseStringToDate(String dateString) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy")
        inputFormat.setLenient(false)
        return inputFormat.parse(dateString)
    }

    static String formatCurrencyWithoutSymbol(String numberString) {
        String cleanNumberString = removeNonNumeric(numberString)
        BigDecimal number = new BigDecimal(cleanNumberString)
        return String.format('%.2f', number).replace('.', ',')
    }
}
