package com.system.integration.controller;

import com.system.io.entity.Account;
import com.system.io.entity.Address;
import com.system.io.entity.Resume;
import com.system.service.ResumeService;
import com.system.shared.dto.ResumeDTO;
import com.system.shared.dto.AddressDTO;
import com.system.ui.model.request.ResumeDetailsRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ResumeControllerTest_INT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResumeService resumeService;

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
    @DisplayName("GET /resumes/userId")
    public void getUserResumes() throws Exception {

        when(resumeService.getAccountResumes(anyString())).thenReturn(resumeDTOList);

        ResultActions result = mockMvc.perform(get("/resumes/{userId}", userId)
                .with(user(userId))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(resumeDetailsRequestModel))
        );

        result.andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andDo(print());


    }



    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}