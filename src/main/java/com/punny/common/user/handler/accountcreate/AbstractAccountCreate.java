package com.punny.common.user.handler.accountcreate;

import com.punny.common.user.external.common.Result;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.enums.AccountCreateType;

/**
 * 创建账户抽象接口
 */
public interface AbstractAccountCreate {
    AccountCreateType getType();
    Result<Void> create(UserAccountDto userAccountDto);
}
