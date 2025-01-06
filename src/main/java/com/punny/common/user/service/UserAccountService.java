package com.punny.common.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.punny.common.user.entity.UserAccount;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author Punny
* @description 针对表【user_account(用户账号表，保存用户账以及与账号相关的敏感信息，是用户的站内凭证)】的数据库操作Service实现
* @createDate 2025-01-05 16:22:05
*/
@Service
public class UserAccountService extends ServiceImpl<UserAccountMapper, UserAccount>{
    /**
     * 获取验证码
     * @param param
     * @return
     */
    public Result<?> getVerifyCode(String param, AccountCreateType createType) {
        return null;
    }
}




