package com.mini.asaas.utils

import grails.compiler.GrailsCompileStatic

import java.text.SimpleDateFormat

@GrailsCompileStatic
class DateUtils {
    public static String formatDate(Date date, String outputFormatString = "dd/MM/yyyy") {
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatString)
        return outputFormat.format(date)
    }
}