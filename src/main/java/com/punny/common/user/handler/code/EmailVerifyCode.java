package com.punny.common.user.handler.code;

import cn.hutool.core.util.RandomUtil;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailVerifyCode implements AbstractCode{
    private final VerifyCodeResolver verifyCodeResolver;
    EmailVerifyCode(VerifyCodeResolver verifyCodeResolver){
        this.verifyCodeResolver = verifyCodeResolver;
    }

    @Override
    public AccountCreateType getType() {
        return AccountCreateType.EMAIL;
    }

    @Override
    public Result<?> setAndSent(String email, AccountCreateType type) {
        String verifyCode = RandomUtil.randomNumbers(6);
        verifyCodeResolver.setVerifyCode(email, verifyCode);
        return sent(email,verifyCode);
    }

    public Result<?> sent(String email, String verifyCode) {
        // TODO 以后具体的验证码发送逻辑写在这里
        return Result.success("验证码发送成功!");
    }
}
