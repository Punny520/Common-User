package com.punny.commonuser.handler.accountcreate;

import com.punny.commonuser.common.Result;
import com.punny.commonuser.dto.UserAccountDto;
import com.punny.commonuser.enums.AccountCreateType;

public interface AccountCreate {
    AccountCreateType getType();
    Result<Void> create(UserAccountDto userAccountDto);
}
