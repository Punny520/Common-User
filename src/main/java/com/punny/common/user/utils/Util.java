package com.punny.common.user.utils;

import cn.hutool.crypto.digest.DigestUtil;

public class Util {
    public static String passwordEncrypt(String password) {
        String salt = "dO-nOt-eVen-tRy.";
        return DigestUtil.sha256Hex(DigestUtil.sha256Hex(password)+salt);
    }
}
