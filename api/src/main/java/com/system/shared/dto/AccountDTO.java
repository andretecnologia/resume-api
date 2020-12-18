package com.system.shared.dto;

import java.util.List;

public class AccountDTO {

    private Long id;
    private String accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;

    private List<ResumeDTO> resumes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<ResumeDTO> getResumes() {
        return resumes;
    }

    public void setResumes(List<ResumeDTO> resumes) {
        this.resumes = resumes;
    }
}
