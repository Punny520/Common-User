package com.punny.common.user.handler.code;

import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.Result;

/**
 * 抽象的验证码接口
 */
public interface AbstractCode {
    AccountCreateType getType();
    Result<?> setAndSent(String param, AccountCreateType type);
}
