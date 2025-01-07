package com.punny.common.user.handler.autofill;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
@Slf4j
public class MPFieldAutoFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("执行插入填充...");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
        this.setFieldValByName("deleted", false, metaObject);
        this.setFieldValByName("enable", true, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("执行更新填充...");
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
