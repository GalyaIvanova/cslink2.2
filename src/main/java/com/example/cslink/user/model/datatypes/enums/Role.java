package com.example.cslink.user.model.datatypes.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TENANT("TNT", "TENANT"),
    CUSTOMER("CST", "CUSTOMER"),
    COSMETOLOGIST("CMG", "COSMETOLOGIST");
    private String code;
    private String name;

    Role(String code, String name) {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code=code;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getAuthority() {
        return code;
    }
}
