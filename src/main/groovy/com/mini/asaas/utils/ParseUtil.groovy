package com.mini.asaas.utils

import java.util.Date
import grails.converters.JSON

class ParseUtil {
    static Date date(String dateString) {
        JSON.parse(dateString)
    }
}
