package com.system.service.impl;

import com.system.exceptions.ResumeServiceException;
import com.system.io.entity.Address;
import com.system.io.entity.Resume;
import com.system.io.entity.Account;
import com.system.io.repository.ResumeRepository;
import com.system.io.repository.AccountRepository;
import com.system.shared.Utils;
import com.system.shared.dto.ResumeDTO;
import com.system.shared.dto.AddressDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class ResumeServiceImplTest {

    @InjectMocks
    ResumeServiceImpl resumeService;

    @Mock
    AccountRepository accountRepository;
    @Mock
    ResumeRepository resumeRepository;
    @Mock
    Utils utils;

    Account account;
    Resume resume;
    ResumeDTO resumeDTO;
    AddressDTO addressDTO;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    String resumeId = "asdasgasda21";
    String addressId = "asdnsalfgn12312";

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

        addressDTO = new AddressDTO();
        addressDTO.setName("address1");
        addressDTO.setAddressId(addressId);

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

        account.getResumes().add(resume);
        resume.setAccount(account);
    }

    @Test
    @DisplayName("Test - Add Resume")
    void testAddResume() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(account);
        when(utils.generateAddressId(anyInt())).thenReturn(addressId);
        when(utils.generateResumeId(anyInt())).thenReturn(resumeId);
        when(resumeRepository.save(any(Resume.class))).thenReturn(resume);

        ResumeDTO resumeDTO = new ResumeDTO();
        resumeDTO.setType("type1");
        resumeDTO.setName("resume1");
        resumeDTO.setAddress(addressDTO);

        ResumeDTO storedResume = resumeService.addResume(resumeDTO, userId);

        assertNotNull(storedResume);
        assertEquals(storedResume.getName(), resume.getName());
        assertEquals(storedResume.getType(), resume.getType());
        assertNotNull(storedResume.getResumeId());
        assertEquals(storedResume.getResumeId(), resume.getResumeId());
        assertEquals(storedResume.getAddress().getName(), resume.getAddress().getName());
        assertEquals(storedResume.getAddress().getAddressId(), resume.getAddress().getAddressId());
        assertNotNull(storedResume.getAddress().getAddressId());
    }

    @Test
    @DisplayName("Test - Add Resume_UsernameNotFoundException")
    void testAddResume_UsernameNotFoundException() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> {
                    resumeService.addResume(resumeDTO, userId);
                }
        );

    }

    @Test
    @DisplayName("Test - Update Resume_ResumeServiceException")
    void testUpdateResume_ResumeServiceException() {

        when(accountRepository.findByAccountId(anyString())).thenReturn(null);

        assertThrows(ResumeServiceException.class,
                () -> {
                    resumeService.updateResume(resumeDTO, userId);
                }
        );


    }

    @Test
    @DisplayName("Test - DeleteByResumeId")
    void testDeleteResumeById() {
        when(resumeRepository.findByResumeId(anyString())).thenReturn(resume);
        when(accountRepository.findByAccountId(anyString())).thenReturn(account);

        resumeService.deleteResumeById(userId, resumeId);


        verify(accountRepository, times(1)).findByAccountId(userId);
        verify(resumeRepository, times(1)).deleteResumeByResumeId(resumeId);

    }

    @Test
    @DisplayName("Test - DeleteByResumeId_ResumeServiceException")
    void testDeleteResumeById_ResumeServiceException() {
        when(resumeRepository.findByResumeId(anyString())).thenReturn(null);

        assertThrows(ResumeServiceException.class,
                () -> {
                    resumeService.updateResume(resumeDTO, userId);
                }
        );

    }

    @Test
    @DisplayName("Test - getResumeById")
    void testGetResumeById() {
        when(resumeRepository.findByResumeId(anyString())).thenReturn(resume);

        ResumeDTO resumeById = resumeService.getResumeById(resumeId);

        assertNotNull(resumeById);
        assertEquals(resumeById.getName(), resume.getName());
        assertEquals(resumeById.getType(), resume.getType());
        assertEquals(resumeById.getResumeId(), resume.getResumeId());
        assertEquals(resumeById.getAddress().getName(), resume.getAddress().getName());
        assertEquals(resumeById.getAddress().getAddressId(), resume.getAddress().getAddressId());

    }

    @Test
    @DisplayName("Test - GET Resume by Id _ ResumeServiceException")
    void testGetResumeById_ResumeServiceException() {
        when(resumeRepository.findByResumeId(anyString())).thenReturn(null);

        assertThrows(ResumeServiceException.class,
                () -> {
                    resumeService.updateResume(resumeDTO, userId);
                }
        );

    }

    @Test
    @DisplayName("Test - get User resumes")
    void testGetUserResumes() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(account);

        List<ResumeDTO> userResumes = resumeService.getAccountResumes(userId);

        assertEquals(userResumes.size(), account.getResumes().size());
        verify(accountRepository, times(1)).findByAccountId(userId);

    }


    @Test
    @DisplayName("Test - GET User resumes_ResumeServiceException")
    void testGetUserResumes_ResumeServiceException() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(null);

        assertThrows(ResumeServiceException.class,
                () -> {
                    resumeService.updateResume(resumeDTO, userId);
                }
        );

    }

}