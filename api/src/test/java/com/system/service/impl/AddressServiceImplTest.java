package com.system.service.impl;

import com.system.io.entity.Address;
import com.system.io.entity.Resume;
import com.system.io.repository.ResumeRepository;
import com.system.shared.dto.AddressDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AddressServiceImplTest {


    @InjectMocks
    AddressServiceImpl addressService;

    @Mock
    ResumeRepository resumeRepository;

    Resume resume;
    Address address;

    String resumeId = "asdasgasda21";
    String addressId = "asdnsalfgn12312";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        address = new Address();
        address.setId(1L);
        address.setAddressId(addressId);
        address.setName("address1");

        resume = new Resume();
        resume.setId(1L);
        resume.setName("resume1");
        resume.setType("type1");
        resume.setResumeId(resumeId);
        resume.setAddress(address);
    }

    @Test
    @DisplayName("Test - get address")
    void getAddress() {

        when(resumeRepository.findByResumeId(anyString())).thenReturn(resume);

        AddressDTO addressDTO = addressService.getAddress(resumeId);

        assertNotNull(addressDTO);
        assertEquals(addressDTO.getName(), address.getName());

        verify(resumeRepository, times(1)).findByResumeId(resumeId);



    }
}