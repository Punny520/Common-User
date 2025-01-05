package com.punny.commonuser.handler.accountcreate;

import com.punny.commonuser.common.Result;
import com.punny.commonuser.dto.UserAccountDto;
import com.punny.commonuser.enums.AccountCreateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountCreateByEmail implements AccountCreate {
    @Override
    public AccountCreateType getType() {
        return AccountCreateType.EMAIL;
    }

    @Override
    public Result<Void> create(UserAccountDto userAccountDto) {
        log.info("start create account:[{}] by email:[{}]"
                ,userAccountDto.getAccount()
                ,userAccountDto.getEmail());
        return null;
    }
}
