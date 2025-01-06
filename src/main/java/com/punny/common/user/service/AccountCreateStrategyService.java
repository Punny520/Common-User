package com.punny.common.user.service;

import com.punny.common.user.external.common.Result;
import com.punny.common.user.external.common.utils.SpringContextUtil;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.handler.accountcreate.AbstractAccountCreate;
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
    public Result<Void> create(UserAccountDto userAccountDto) {
        if(userAccountDto.getCreateType() == null){
            throw new IllegalArgumentException("createType is null");
        }
        AbstractAccountCreate handler = ACCOUNT_CREATE_HANDLER_MAP.get(userAccountDto.getCreateType());
        if (handler == null) {
            throw new IllegalArgumentException("no such create type " + userAccountDto.getCreateType());
        }
        return handler.create(userAccountDto);
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
