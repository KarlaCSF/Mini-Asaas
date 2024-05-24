package com.mini.asaas.utils

import java.text.SimpleDateFormat
import java.util.Date
import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class ParseUtil {
    static Date date(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd")
        dateFormat.setLenient(false)
        return dateFormat.parse(dateString)
    }
}
