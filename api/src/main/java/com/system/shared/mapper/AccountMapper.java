package com.system.shared.mapper;

import com.system.io.entity.Account;
import com.system.shared.dto.AccountDTO;
import com.system.ui.model.request.AccountDetailRequestModel;
import com.system.ui.model.response.AccountRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper( AccountMapper.class );

    AccountDTO userDetailsToUserDto(AccountDetailRequestModel accountDetailRequestModel);
    AccountRest userDtoToUserRest(AccountDTO accountDTO);
    AccountDTO accountToAccountDto(Account account);
    Account userDtoToUser(AccountDTO accountDTO);


}
