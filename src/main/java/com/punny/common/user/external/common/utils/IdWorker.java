package com.punny.common.user.external.common.utils;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * 使用雪花算法生成id
 * 分布式情况下，需要修改对应的workerId和dataCenterId
 */
@Component
public class IdWorker implements IdentifierGenerator {
    private static final Snowflake snowflake = new Snowflake(0, 0);

    @Override
    public Number nextId(Object entity) {
        return snowflake.nextId();
    }
}
