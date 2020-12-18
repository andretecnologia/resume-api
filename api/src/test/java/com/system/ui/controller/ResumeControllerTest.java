package com.system.ui.controller;

import com.system.io.entity.Account;
import com.system.io.entity.Address;
import com.system.io.entity.Resume;
import com.system.service.ResumeService;
import com.system.shared.dto.ResumeDTO;
import com.system.shared.dto.AddressDTO;
import com.system.ui.model.request.ResumeDetailsRequestModel;
import com.system.ui.model.response.ResumeRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ResumeControllerTest {

    @InjectMocks
    ResumeController resumeController;

    @Mock
    ResumeService resumeService;

    Account account;
    Resume resume;
    ResumeDTO resumeDTO;
    ResumeDetailsRequestModel resumeDetailsRequestModel;
    AddressDTO addressDTO;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    String resumeId = "asdasgasda21";
    String addressId = "asdnsalfgn12312";

    List<ResumeDTO> resumeDTOList = new ArrayList<>();

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

        resumeDetailsRequestModel = new ResumeDetailsRequestModel();
        resumeDetailsRequestModel.setName("resume1");
        resumeDetailsRequestModel.setType("type1");

        account.getResumes().add(resume);
        resume.setAccount(account);

        resumeDTO = new ResumeDTO();
        resumeDTO.setName("resume1");
        resumeDTO.setType("type1");
        resumeDTO.setResumeId(resumeId);
        resumeDTO.setAddress(addressDTO);

        resumeDTOList.add(resumeDTO);
    }

    @Test
    void getUserResumes() {
        when(resumeService.getAccountResumes(anyString())).thenReturn(resumeDTOList);

        List<ResumeRest> userResumes = resumeController.getaccountResumes(userId);

        assertEquals(userResumes.size(), resumeDTOList.size());

    }

    @Test
    void getResumeById() {
        when(resumeService.getResumeById(anyString())).thenReturn(resumeDTO);

        ResumeRest resumeById = resumeController.getResumeById(resumeId);

        assertNotNull(resumeById);
        assertEquals(resumeById.getResumeId(), resumeDTO.getResumeId());
        assertEquals(resumeById.getAddress().getAddressId(), resumeDTO.getAddress().getAddressId());
        assertEquals(resumeById.getType(), resumeDTO.getType());
    }

    @Test
    void deleteResume() {
        when(resumeService.deleteResumeById(anyString(), anyString())).thenReturn(resumeDTOList);

        List<ResumeRest> resumeRests = resumeController.deleteResume(userId, resumeId);

        assertEquals(resumeRests.size(), resumeDTOList.size());

        verify(resumeService, times(1)).deleteResumeById(anyString(), anyString());
    }

//    @Test
//    void createResume() {
//        when(resumeService.addResume(any(ResumeDTO.class), anyString())).thenReturn(resumeDTO);
//
//        ResumeRest resumeRest = resumeController.createResume(resumeDetailsRequestModel, resumeId);
//
//        assertNotNull(resumeRest);
//        assertEquals(resumeRest.getName(), resumeDTO.getName());
//        assertEquals(resumeRest.getType(), resumeDTO.getType());
//        assertEquals(resumeRest.getAddress().getAddressId(), resumeDTO.getAddress().getAddressId());
//
//    }

    @Test
    void updateResume() {
        when(resumeService.updateResume(any(ResumeDTO.class), anyString())).thenReturn(resumeDTO);

        ResumeRest resumeRest = resumeController.updateResume(resumeDetailsRequestModel, resumeId);

        assertNotNull(resumeRest);
        assertEquals(resumeRest.getName(), resumeDTO.getName());
        assertEquals(resumeRest.getType(), resumeDTO.getType());
        assertEquals(resumeRest.getAddress().getAddressId(), resumeDTO.getAddress().getAddressId());
    }
}