package com.system.io.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Resume {

    @Id
    @GeneratedValue
    private Long id;
    private String resumeId;
    private String type;
    private String name;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Education> educations = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Account account;

    @OneToOne(mappedBy = "resume", cascade = CascadeType.ALL)
    private Address address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;

        if (account != null && !account.getResumes().contains(this)) {
            account.getResumes().add(this);
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public void addEducation(Education education) {
        this.educations.add(education);

        if (!this.equals(education.getResume())) {
            education.setResume(this);
        }
    }
}
