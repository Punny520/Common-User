package com.punny.common.user.handler.code;

import cn.hutool.core.util.RandomUtil;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 手机验证码
 */
@Component
@Slf4j
public class PhoneVerifyCode implements AbstractCode {
    private final VerifyCodeResolver verifyCodeResolver;
    PhoneVerifyCode(VerifyCodeResolver verifyCodeResolver){
        this.verifyCodeResolver = verifyCodeResolver;
    }

    @Override
    public AccountCreateType getType() {
        return AccountCreateType.PHONE;
    }

    @Override
    public Result<?> setAndSent(String phone, AccountCreateType type) {
        String verifyCode = RandomUtil.randomNumbers(6);
        verifyCodeResolver.setVerifyCode(phone, verifyCode);
        return sent(phone,verifyCode);
    }

    public Result<?> sent(String phone,String verifyCode) {
        // TODO 以后具体的验证码发送逻辑写在这里
        return Result.success("验证码发送成功!");
    }
}
