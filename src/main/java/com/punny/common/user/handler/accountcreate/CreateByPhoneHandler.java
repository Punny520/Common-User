package com.punny.common.user.handler.accountcreate;

import com.punny.common.user.external.common.Result;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.utils.ArgumentChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 根据手机创建账号
 */
@Component
@Slf4j
public class CreateByPhoneHandler implements AbstractAccountCreate {
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
