package com.system.ui.model.response;

public class ResumeRest {

    private String resumeId;
    private String type;
    private String name;

    private AddressRest address;


    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

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

    public AddressRest getAddress() {
        return address;
    }

    public void setAddress(AddressRest address) {
        this.address = address;
    }
}
