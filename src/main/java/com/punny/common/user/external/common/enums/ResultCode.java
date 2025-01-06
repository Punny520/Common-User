package com.punny.common.user.external.common.enums;

/**
 * 结果状态码枚举类
 * 1：表示请求成功
 * -1：表示请求失败
 */
public enum ResultCode {
    SUCCESS(1),
    FAILURE(-1);
    private final int code;
    ResultCode(int code) {
        this.code = code;
    }
}
