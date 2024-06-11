package com.mini.asaas.utils

class Util {
    public static removeNonNumeric(String text) {
        return text.replaceAll("[^\\d]", "")
    }
}
