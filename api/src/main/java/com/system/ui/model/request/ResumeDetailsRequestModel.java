package com.system.ui.model.request;

public class ResumeDetailsRequestModel {

    private String type;
    private String name;

    private AddressDetailsRequestModel address;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressDetailsRequestModel getAddress() {
        return address;
    }

    public void setAddress(AddressDetailsRequestModel address) {
        this.address = address;
    }


}
