package com.punny.commonuser.controller;

import com.punny.commonuser.common.Result;
import com.punny.commonuser.dto.UserAccountDto;
import com.punny.commonuser.service.AccountCreateStrategyService;
import com.punny.commonuser.service.UserAccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/account")
public class UserAccountController {
    private final UserAccountService userAccountService;
    private final AccountCreateStrategyService accountCreateStrategyService;

    UserAccountController(UserAccountService userAccountService,
                          AccountCreateStrategyService accountCreateStrategyService) {
        this.userAccountService = userAccountService;
        this.accountCreateStrategyService = accountCreateStrategyService;
    }
    @PostMapping
    public Result<Void> createAccount(@RequestBody UserAccountDto userAccountDto) {
        return accountCreateStrategyService.create(userAccountDto);
    }
}
