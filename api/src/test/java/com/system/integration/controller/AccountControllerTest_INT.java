package com.system.integration.controller;

import com.system.service.AccountService;
import com.system.shared.dto.AccountDTO;
import com.system.ui.model.request.AccountDetailRequestModel;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest_INT {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;


    AccountDTO accountDTO;
    AccountDetailRequestModel accountDetailRequestModel;
    String accountId = "ajshdjsadhjsad";

    @BeforeEach
    void setUp() {
        accountDTO = new AccountDTO();

        accountDTO.setId(1L);
        accountDTO.setFirstName("John");
        accountDTO.setLastName("Jones");
        accountDTO.setEmail("test@gmail.com");
        accountDTO.setAccountId(accountId);
        accountDTO.setPassword("123456");

        accountDetailRequestModel = new AccountDetailRequestModel();
        accountDetailRequestModel.setEmail("test@gmail.com");
        accountDetailRequestModel.setFirstName("John");
        accountDetailRequestModel.setLastName("Jones");
        accountDetailRequestModel.setPassword("123456");

    }

    @Test
    @DisplayName("POST /accounts")
    public void createAccount() throws Exception {

        when(accountService.create(any(AccountDTO.class))).thenReturn(accountDTO);

        ResultActions result = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(accountDetailRequestModel))
        );

        result.andExpect(status().isCreated())
                .andExpect(jsonPath("$.accountId").value(accountId))
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Jones"))
                .andExpect(jsonPath("$.email").value("test@gmail.com"));

    }


    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}