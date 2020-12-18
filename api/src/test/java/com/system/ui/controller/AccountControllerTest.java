package com.system.ui.controller;

import com.system.service.impl.AccountServiceImpl;
import com.system.shared.dto.AccountDTO;
import com.system.ui.model.request.AccountDetailRequestModel;
import com.system.ui.model.response.AccountRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @InjectMocks
    AccountController accountController;

    @Mock
    AccountServiceImpl userService;

    AccountDTO accountDTO;
    AccountDetailRequestModel accountDetailRequestModel;
    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        accountDTO = new AccountDTO();

        accountDTO.setId(1L);
        accountDTO.setFirstName("John");
        accountDTO.setLastName("Jones");
        accountDTO.setEmail("test@test.com");
        accountDTO.setAccountId(userId);
        accountDTO.setEncryptedPassword(encryptedPassword);

        accountDetailRequestModel = new AccountDetailRequestModel();
        accountDetailRequestModel.setEmail("test@test.com");
        accountDetailRequestModel.setFirstName("John");
        accountDetailRequestModel.setLastName("Jones");
        accountDetailRequestModel.setPassword("123456");
    }

    @Test
    void createUser() {
        when(userService.create(any(AccountDTO.class))).thenReturn(accountDTO);

        AccountRest accountRest = accountController.createUser(accountDetailRequestModel);

        assertNotNull(accountRest);
        assertEquals(accountDetailRequestModel.getFirstName(), accountRest.getFirstName());
        assertEquals(accountDetailRequestModel.getLastName(), accountRest.getLastName());
        assertEquals(accountDetailRequestModel.getEmail(), accountRest.getEmail());

        verify(userService, times(1)).create(any(AccountDTO.class));
    }
}