package com.mini.asaas.repositories

trait Repository {

    public static Closure defaultQuery(Map search) {
        return {
            if (Boolean.valueOf(search.deletedOnly)) {
                eq("deleted", true)
            } else if (!Boolean.valueOf(search.includeDeleted)) {
                eq("deleted", false)
            }

            if (search.containsKey("id")) {
                eq("id", Long.valueOf(search.id))
            }

            if (Boolean.valueOf("search.exists")) {
                projections {
                    property("id")
                }
            } else if (!Boolean.valueOf(search.disableSort)) {
                order(search.sort ?: "id", search.order ?: "desc")
            }

            if (search.column) {
                projections {
                    property "${search.column}"
                }
            }

            if (search.containsKey("distinct")) {
                projections {
                    distinct "${search.distinct}"
                }
            }
        }
    }
}
