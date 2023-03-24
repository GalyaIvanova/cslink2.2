package com.example.cslink.user.model.datatypes.enums;

public enum Role {
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

}
