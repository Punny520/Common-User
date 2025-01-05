package com.punny.commonuser.handler.accountcreate;

import com.google.common.base.Preconditions;
import com.punny.commonuser.common.Result;
import com.punny.commonuser.dto.UserAccountDto;
import com.punny.commonuser.enums.AccountCreateType;
import com.punny.commonuser.utils.ArgumentChecker;
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
        try{
            ArgumentChecker.userAccountDtoChecker(userAccountDto);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
        return null;
    }
}
