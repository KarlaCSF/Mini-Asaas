package com.mini.asaas.namedqueries

class NamedQueries {

    static applyDefaultQuery(delegate) {
        delegate.eq('deleted', false)
    }
}
