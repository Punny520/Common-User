package com.punny.common.user.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.entity.UserAccount;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.handler.code.VerifyCodeResolver;
import com.punny.common.user.mapper.UserAccountMapper;
import com.punny.common.user.mapper.convert.UserAccountConvert;
import com.punny.common.user.utils.Util;
import org.springframework.stereotype.Service;

/**
* @author Punny
* @description 针对表【user_account(用户账号表，保存用户账以及与账号相关的敏感信息，是用户的站内凭证)】的数据库操作Service实现
* @createDate 2025-01-05 16:22:05
*/
@Service
public class UserAccountService extends ServiceImpl<UserAccountMapper, UserAccount>{
    private final VerifyCodeStrategyService verifyCodeStrategyService;
    private final UserAccountMapper userAccountMapper;

    public UserAccountService(VerifyCodeStrategyService verifyCodeStrategyService, UserAccountMapper userAccountMapper) {
        this.verifyCodeStrategyService = verifyCodeStrategyService;
        this.userAccountMapper = userAccountMapper;
    }
    /**
     * 获取验证码
     * @param param 邮箱或者手机号
     * @return
     */
    public Result<?> getVerifyCode(String param, AccountCreateType type) {
        return verifyCodeStrategyService.setAndSent(param, type);
    }

    /**
     * 用户登录
     * @param userAccountDto 前端信息
     * @return
     */
    public Result<?> login(UserAccountDto userAccountDto) {
        userAccountDto.setPassword(Util.passwordEncrypt(userAccountDto.getPassword()));
        UserAccount userAccount = UserAccountConvert.INSTANCE.convertDto(userAccountDto);
        UserAccount user = userAccountMapper.selectOne(new QueryWrapper<>(userAccount));
        if(user == null || user.getId() == null) {
            return Result.failure("登录失败，账号或者密码错误");
        }
        if(user.getEnable().equals(Boolean.FALSE)){
            return Result.failure("登录失败，账号不可用");
        }
        StpUtil.login(user.getId());
        return Result.success(StpUtil.getTokenValue());
    }
}




