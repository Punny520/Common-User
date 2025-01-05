package com.punny.commonuser.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }
    public ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) {
        return APPLICATION_CONTEXT.getBeansOfType(clazz);
    }
}
