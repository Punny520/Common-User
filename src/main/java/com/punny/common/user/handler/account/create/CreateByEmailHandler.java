package com.punny.common.user.handler.account.create;

import cn.dev33.satoken.stp.StpUtil;
import com.punny.common.user.common.Constant;
import com.punny.common.user.entity.UserAccount;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.utils.RedisUtil;
import com.punny.common.user.mapper.convert.UserAccountConvert;
import com.punny.common.user.service.UserAccountService;
import com.punny.common.user.utils.ArgumentChecker;
import com.punny.common.user.utils.Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 根据邮箱创建账号
 */
@Component
@Slf4j
public class CreateByEmailHandler implements AbstractAccountCreate {
    private final UserAccountService userAccountService;
    private final ArgumentChecker argumentChecker;

    CreateByEmailHandler(UserAccountService userAccountService,
                         ArgumentChecker argumentChecker){
        this.userAccountService = userAccountService;
        this.argumentChecker = argumentChecker;
    }
    @Override
    public AccountCreateType getType() {
        return AccountCreateType.EMAIL;
    }

    @Override
    public Result<?> create(UserAccountDto userAccountDto) {
        log.info("start create account:[{}] by email:[{}]"
                ,userAccountDto.getAccount()
                ,userAccountDto.getEmail());
            try{
                //参数检查
                argumentChecker.userAccountDtoCheckBeforeCreate(userAccountDto);
                //验证码校验
                String verifyCode = RedisUtil.getValue(getVerifyCodeKey(userAccountDto.getEmail()));
                argumentChecker.verifyCodeVerification(userAccountDto.getVerifyCode(), verifyCode);

                UserAccount userAccount = UserAccountConvert.INSTANCE.convertDto(userAccountDto);
                //密码加密
                userAccount.setPassword(Util.passwordEncrypt(userAccount.getPassword()));
                userAccount.setPhone("");//禁止传入非法的phone
                userAccountService.save(userAccount);
                StpUtil.login(userAccount.getId());
            }catch (Exception e){
                return Result.failure(e.getMessage());
            }
            return Result.success("注册成功，已自动登录。",StpUtil.getTokenValue());
    }
    private String getVerifyCodeKey(String email){
        return Constant.ACCOUNT_CREATE_VERIFY_CODE + email;
    }
}
