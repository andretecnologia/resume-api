package com.system.io.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "accounts")
public class Account {


    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String accountId;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;

    @OneToMany(mappedBy ="account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Resume> resumes = new ArrayList<>();


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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }

    public void addResume(Resume resume) {
        this.resumes.add(resume);

        if (!this.equals(resume.getAccount())) {
            resume.setAccount(this);
        }
    }

}
