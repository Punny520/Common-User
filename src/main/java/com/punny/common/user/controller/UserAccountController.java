package com.punny.common.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.service.AccountCreateStrategyService;
import com.punny.common.user.service.UserAccountService;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.dto.UserAccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/account")
@Slf4j
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final AccountCreateStrategyService accountCreateStrategyService;

    UserAccountController(UserAccountService userAccountService,
                          AccountCreateStrategyService accountCreateStrategyService) {
        this.userAccountService = userAccountService;
        this.accountCreateStrategyService = accountCreateStrategyService;
    }
    @PostMapping
    public Result<?> createAccount(@RequestBody UserAccountDto userAccountDto) {
        log.info("注册账号：{}", userAccountDto);
        return accountCreateStrategyService.create(userAccountDto);
    }

    @GetMapping("/code")
    public Result<?> getVerifyCode(@RequestParam(value = "phone",required = false) String phone,
                                   @RequestParam(value = "email",required = false) String email){
        if(!StrUtil.isEmpty(phone)&&!StrUtil.isEmpty(email)){
            return Result.failure("只能选择一种注册方式");
        }
        if(StrUtil.isEmpty(phone)&&StrUtil.isEmpty(email)){
            return Result.failure("请输入手机号或者邮箱");
        }
        AccountCreateType type;
        String param;

        if(!StrUtil.isEmpty(phone)){
            param = phone;
            type = AccountCreateType.PHONE;
        }else{
            param = email;
            type = AccountCreateType.EMAIL;
        }
        return userAccountService.getVerifyCode(param,type);
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody UserAccountDto userAccountDto) {
        log.info("user login: {}", userAccountDto);
        return userAccountService.login(userAccountDto);
    }

    @GetMapping("/logout")
    public Result<?> logout() {
        StpUtil.logout();
        return Result.success();
    }
}
