package com.punny.common.user.controller;

import com.punny.common.user.service.AccountCreateStrategyService;
import com.punny.common.user.service.UserAccountService;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.dto.UserAccountDto;
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
