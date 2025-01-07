package com.punny.common.user.service;

import com.google.common.base.Preconditions;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.external.common.utils.SpringContextUtil;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.handler.account.create.AbstractAccountCreate;
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
    private static final Map<AccountCreateType, AbstractAccountCreate> ACCOUNT_CREATE_HANDLER_MAP = new ConcurrentHashMap<>();
    public Result<?> create(UserAccountDto userAccountDto) {
        try{
            Preconditions.checkNotNull(userAccountDto,"error!");
            Preconditions.checkNotNull(userAccountDto.getAccount(),"请选择一种注册方式");
            AbstractAccountCreate handler = ACCOUNT_CREATE_HANDLER_MAP.get(userAccountDto.getCreateType());
            Preconditions.checkNotNull(handler,"no such create type " + userAccountDto.getCreateType());
            return handler.create(userAccountDto);
        }catch (Exception e){
            return Result.failure(e.getMessage());
        }
    }

    @Override
    public void run(String... args){
        log.info("start inject account create strategy handler...");
        Map<String, AbstractAccountCreate> handlerMap = SpringContextUtil.getBeansOfType(AbstractAccountCreate.class);
        handlerMap.forEach((beanName, accountCreate) -> {
            ACCOUNT_CREATE_HANDLER_MAP.put(accountCreate.getType(), accountCreate);
        });
        log.info("account create strategy handler injection finish.");
    }
}
