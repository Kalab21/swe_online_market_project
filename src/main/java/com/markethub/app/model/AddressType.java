package com.markethub.app.model;

public enum AddressType {
    BILLING("BILLING"), SHIPPING("SHIPPING");

    private String type;

    AddressType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
}
