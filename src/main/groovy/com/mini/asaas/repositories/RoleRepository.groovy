package com.mini.asaas.repositories

import com.mini.asaas.repositories.Repository
import com.mini.asaas.user.Role

import grails.gorm.DetachedCriteria

class RoleRepository implements Repository {

    public static DetachedCriteria<Role> query(Map search) {
        return Role.where(defaultQuery(search))
    }
    
    public static List<Role> listAll() {
        return RoleRepository.query([empty: null]).list()
    }
}
