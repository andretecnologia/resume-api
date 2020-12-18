package com.system.service.impl;

import com.system.exceptions.AccountServiceException;
import com.system.io.entity.Account;
import com.system.io.repository.AccountRepository;
import com.system.service.AccountService;
import com.system.shared.Utils;
import com.system.shared.dto.AccountDTO;
import com.system.shared.mapper.AccountMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final Utils utils;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, Utils utils, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.utils = utils;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getEncryptedPassword(), new ArrayList<>());
    }


    @Override
    public AccountDTO create(AccountDTO accountDTO) {
        if (accountRepository.findByEmail(accountDTO.getEmail()) != null) {
            throw new AccountServiceException("Record with " + accountDTO.getAccountId() + " already exists!");
        }

        Account account = AccountMapper.INSTANCE.userDtoToUser(accountDTO);

        account.setEncryptedPassword(passwordEncoder.encode(accountDTO.getPassword()));
        account.setAccountId(utils.generateAccountId(30));

        return AccountMapper.INSTANCE.accountToAccountDto(accountRepository.save(account));
    }

    @Override
    public AccountDTO getAccountByAccountId(String accountId) {
        Account account = accountRepository.findByAccountId(accountId);
        if (account == null) {
            throw new UsernameNotFoundException(accountId);
        }

        return AccountMapper.INSTANCE.accountToAccountDto(account);
    }


    @Override
    public String getAccountByEmail(String email) {
        Account account = accountRepository.findByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException(email);
        }

        return account.getAccountId();
    }


}
