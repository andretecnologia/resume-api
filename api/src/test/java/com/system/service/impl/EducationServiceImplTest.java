package com.system.service.impl;

import com.system.io.entity.Account;
import com.system.io.entity.Address;
import com.system.io.entity.Education;
import com.system.io.entity.Resume;
import com.system.io.repository.ResumeRepository;
import com.system.io.repository.EducationRepository;
import com.system.io.repository.AccountRepository;
import com.system.shared.Utils;
import com.system.shared.dto.AccountDTO;
import com.system.shared.dto.EducationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class EducationServiceImplTest {

    @InjectMocks
    EducationServiceImpl educationService;

    @Mock
    AccountRepository accountRepository;
    @Mock
    EducationRepository educationRepository;
    @Mock
    ResumeRepository resumeRepository;
    @Mock
    Utils utils;


    Account account;
    AccountDTO accountDTO;
    Resume resume;
    Education education;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    String resumeId = "asdasgasda21";
    String addressId = "asdnsalfgn12312";
    String educationId = "adhjsahdojsar3";


    List<Education> educations = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        account = new Account();

        account.setId(1L);
        account.setFirstName("John");
        account.setLastName("Jones");
        account.setEmail("test@test.com");
        account.setAccountId(userId);
        account.setEncryptedPassword(encryptedPassword);

        Address address = new Address();
        address.setId(1L);
        address.setAddressId(addressId);
        address.setName("address1");

        resume = new Resume();
        resume.setId(1L);
        resume.setName("resume1");
        resume.setType("type1");
        resume.setResumeId(resumeId);
        resume.setAddress(address);

        education = new Education();
        education.setId(1L);
        education.setName("education1");
        education.setEducationId(educationId);

        education.setResume(resume);
        resume.addEducation(education);

        account.getResumes().add(resume);
        resume.setAccount(account);

        educations.add(education);
    }

    @Test
    @DisplayName("Test - get Educations For User Resumes")
    void getEducationsForUserResumes() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(account);

        List<EducationDTO> educations = educationService.getEducationsForAccountResumes(userId);

        List<Education> entityEducations = new ArrayList<>();

        for (Resume resume : account.getResumes()) {
            entityEducations.addAll(resume.getEducations());
        }

        assertEquals(educations.size(), entityEducations.size());
        verify(accountRepository, times(1)).findByAccountId(userId);

    }

    @Test
    @DisplayName("Test - get Education by educationId")
    void getEducationById() {
        when(educationRepository.findByEducationId(anyString())).thenReturn(education);

        EducationDTO educationById = educationService.getEducationById(educationId);

        assertNotNull(educationById);
        assertEquals(educationById.getName(), education.getName());
        assertEquals(educationById.getEducationId(), education.getEducationId());
    }

    @Test
    @DisplayName("Test - UPDATE education")
    void updateEducation() {
        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setName("education2");
        Education education2 = new Education();
        education2.setName("education2");
        when(educationRepository.findByEducationId(educationId)).thenReturn(education);
        when(educationRepository.save(any(Education.class))).thenReturn(education2);

        EducationDTO changed = educationService.updateEducation(educationDTO, educationId);

        assertNotNull(changed);
        verify(educationRepository, times(1)).findByEducationId(educationId);
        verify(educationRepository, times(1)).save(education);

    }

    @Test
    @DisplayName("Test - GET Educations for Resume")
    void getEducationsForResume() {
        when(educationRepository.getAllByResume_ResumeId(resumeId)).thenReturn(educations);

        List<EducationDTO> educationsForResume = educationService.getEducationsForResume(resumeId);

        assertEquals(educationsForResume.size(), educations.size());
        verify(educationRepository, times(1)).getAllByResume_ResumeId(resumeId);

    }

    @Test
    @DisplayName("Test - ADD education")
    void addEducation() {
        when(resumeRepository.findByResumeId(resumeId)).thenReturn(resume);
        when(utils.generateEducationId(anyInt())).thenReturn(educationId);
        when(educationRepository.save(any(Education.class))).thenReturn(education);

        EducationDTO educationDTO = new EducationDTO();
        educationDTO.setName("education2");
        educationDTO.setEducationId(educationId);

        EducationDTO saved = educationService.addEducation(educationDTO, resumeId);

        assertNotNull(saved);
        assertEquals(saved.getName(), education.getName());
        assertEquals(saved.getEducationId(), education.getEducationId());

        verify(resumeRepository, times(1)).findByResumeId(resumeId);
        verify(utils, times(1)).generateEducationId(30);

    }

}