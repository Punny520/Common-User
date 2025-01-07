package com.punny.common.user.service;

import com.google.common.base.Preconditions;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.external.common.Result;
import com.punny.common.user.external.common.utils.SpringContextUtil;
import com.punny.common.user.handler.code.AbstractCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class VerifyCodeStrategyService implements CommandLineRunner {
    private static final Map<AccountCreateType, AbstractCode> VERIFY_CODE_HANDLER_MAP = new ConcurrentHashMap<>();
    public Result<?> setAndSent(String param,AccountCreateType type) {
        AbstractCode handler = VERIFY_CODE_HANDLER_MAP.get(type);
        Preconditions.checkNotNull(handler,"no such type "+ type);
        return handler.setAndSent(param,type);
    }

    @Override
    public void run(String... args){
        log.info("start inject verify code strategy handler...");
        Map<String, AbstractCode> handlerMap = SpringContextUtil.getBeansOfType(AbstractCode.class);
        handlerMap.forEach((beanName, codeHandler) -> {
            VERIFY_CODE_HANDLER_MAP.put(codeHandler.getType(), codeHandler);
        });
        log.info("verify code strategy handler injection finish.");
    }
}
