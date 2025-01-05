package com.punny.commonuser.handler.accountcreate;

import com.punny.commonuser.common.Result;
import com.punny.commonuser.dto.UserAccountDto;
import com.punny.commonuser.enums.AccountCreateType;
import com.punny.commonuser.utils.ArgumentChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccountCreateByPhone implements AccountCreate {
    @Override
    public AccountCreateType getType() {
        return AccountCreateType.PHONE;
    }

    @Override
    public Result<Void> create(UserAccountDto userAccountDto) {
        log.info("start create account:[{}] by phone:[{}]"
                ,userAccountDto.getAccount()
                ,userAccountDto.getPhone());
        try{
            ArgumentChecker.userAccountDtoChecker(userAccountDto);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
        return null;
    }
}
