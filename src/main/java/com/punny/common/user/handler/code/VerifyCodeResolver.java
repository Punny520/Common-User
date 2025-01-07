package com.punny.common.user.handler.code;

import com.punny.common.user.common.Constant;
import com.punny.common.user.external.common.utils.RedisUtil;
import com.punny.common.user.utils.ArgumentChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 处理验证码的设置和验证
 */
@Component
@Slf4j
public class VerifyCodeResolver {
    private final ArgumentChecker argumentChecker;
    VerifyCodeResolver(ArgumentChecker argumentChecker) {
        this.argumentChecker = argumentChecker;
    }
    private static final String prefix = Constant.ACCOUNT_CREATE_VERIFY_CODE;
    public void setVerifyCode(String param,String verifyCode){
        log.info("setAndSent:{}-{}",param,verifyCode);
        RedisUtil.setValue(prefix + param,verifyCode);
    }
    public void verification(String param,String currentVerifyCode){
        String exceptVerifyCode = RedisUtil.getValue(prefix + param);
        argumentChecker.verifyCodeVerification(currentVerifyCode,exceptVerifyCode);
    }
}
