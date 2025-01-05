package com.punny.commonuser.service;

import com.punny.commonuser.common.Result;
import com.punny.commonuser.common.utils.SpringContextUtil;
import com.punny.commonuser.dto.UserAccountDto;
import com.punny.commonuser.enums.AccountCreateType;
import com.punny.commonuser.handler.accountcreate.AccountCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用策略模式
 * 根据不同注册方式
 * 选择不同的策略
 */
@Service
@Slf4j
public class AccountCreateStrategyService implements CommandLineRunner {
    private static final Map<AccountCreateType, AccountCreate> ACCOUNT_CREATE_HANDLER_MAP = new ConcurrentHashMap<>();
    public Result<Void> create(UserAccountDto userAccountDto) {
        if(userAccountDto.getCreateType() == null){
            throw new IllegalArgumentException("createType is null");
        }
        AccountCreate handler = ACCOUNT_CREATE_HANDLER_MAP.get(userAccountDto.getCreateType());
        if (handler == null) {
            throw new IllegalArgumentException("no such create type " + userAccountDto.getCreateType());
        }
        return handler.create(userAccountDto);
    }

    @Override
    public void run(String... args){
        log.info("start inject account create strategy handler...");
        Map<String, AccountCreate> handlerMap = SpringContextUtil.getBeansOfType(AccountCreate.class);
        handlerMap.forEach((beanName, accountCreate) -> {
            ACCOUNT_CREATE_HANDLER_MAP.put(accountCreate.getType(), accountCreate);
        });
        log.info("account create strategy handler injection finish.");
    }
}
