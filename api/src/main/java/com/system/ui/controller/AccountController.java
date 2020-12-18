package com.system.ui.controller;


import com.system.exceptions.ResumeServiceException;
import com.system.service.AccountService;
import com.system.shared.dto.AccountDTO;
import com.system.shared.mapper.AccountMapper;
import com.system.ui.model.request.AccountDetailRequestModel;
import com.system.ui.model.response.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountRest createUser(@RequestBody AccountDetailRequestModel userDetails) {

        if (userDetails.getFirstName().isEmpty())
            throw new ResumeServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getMessage());

        AccountDTO accountDTO = AccountMapper.INSTANCE.userDetailsToUserDto(userDetails);
        AccountDTO createdUser = accountService.create(accountDTO);

        return AccountMapper.INSTANCE.userDtoToUserRest(createdUser);
    }


}
