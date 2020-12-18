package com.system.service.impl;

import com.system.exceptions.AccountServiceException;
import com.system.io.entity.Account;
import com.system.io.repository.AccountRepository;
import com.system.shared.Utils;
import com.system.shared.dto.AccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl userService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    Utils utils;

    @Mock
    PasswordEncoder passwordEncoder;

    String userId = "ajshdjsadhjsad";
    String encryptedPassword = "dskjdska721y3213sad";

    Account account;

    AccountDTO accountDTO;


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


        accountDTO = new AccountDTO();
        accountDTO.setFirstName("John");
        accountDTO.setLastName("Jones");
        accountDTO.setPassword("12345678");
        accountDTO.setEmail("test@test.com");

    }


    @Test
    @DisplayName("Test - CREATE User")
    void testCreateUser() {
        when(accountRepository.findByEmail(anyString())).thenReturn(null);
        when(utils.generateResumeId(anyInt())).thenReturn(userId);
        when(passwordEncoder.encode(anyString())).thenReturn(encryptedPassword);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDTO storedUserDetails = userService.create(accountDTO);

        assertNotNull(storedUserDetails);
        assertEquals(account.getFirstName(), storedUserDetails.getFirstName());
        assertEquals(account.getLastName(), storedUserDetails.getLastName());
        assertNotNull(storedUserDetails.getAccountId());
        assertEquals(account.getAccountId(), storedUserDetails.getAccountId());

        verify(utils, times(1)).generateAccountId(30);
        verify(passwordEncoder, times(1)).encode("12345678");
        verify(accountRepository, times(1)).save(any(Account.class));

    }

    @Test
    @DisplayName("Test - UserServiceException")
    void testCreateUserException() {
        when(accountRepository.findByEmail(anyString())).thenReturn(account);

        assertThrows(AccountServiceException.class,
                () -> {
                    userService.create(accountDTO);

                }
        );


    }

    @Test
    void testGetUserByUserId() {

        when(accountRepository.findByAccountId(anyString())).thenReturn(account);

        AccountDTO accountDTO = userService.getAccountByAccountId("test@test.com");

        assertNotNull(accountDTO);
        assertEquals("John", accountDTO.getFirstName());
        assertEquals("John", accountDTO.getFirstName());


    }

    @Test
    void testGetUserByUserId_UsernameNotFoundException() {
        when(accountRepository.findByAccountId(anyString())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class,
                () -> {
                    userService.getAccountByAccountId("12123dfakdksad");

                }
        );

    }

}