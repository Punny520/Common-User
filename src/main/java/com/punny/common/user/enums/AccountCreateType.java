package com.punny.common.user.enums;

import lombok.Getter;

/**
 * 用户创建账号方式
 * 0：使用手机注册
 * 1：使用邮箱注册
 */
@Getter
public enum AccountCreateType {
    PHONE(0),
    EMAIL(1);
    private final int typeKey;
    AccountCreateType(int typeKey) {
        this.typeKey = typeKey;
    }
    public static AccountCreateType getTypeByKey(int typeKey) {
        AccountCreateType[] types = AccountCreateType.values();
        for (AccountCreateType type : types) {
            if(type.typeKey == typeKey) {
                return type;
            }
        }
        throw new IllegalArgumentException("No such typeKey: " + typeKey);
    }
}
