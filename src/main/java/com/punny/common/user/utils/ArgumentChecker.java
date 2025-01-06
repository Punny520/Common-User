package com.punny.common.user.utils;

import com.google.common.base.Preconditions;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.enums.AccountCreateType;

import java.util.regex.Pattern;

/**
 * 参数校验工具类
 */
public class ArgumentChecker {
    // 正则表达式常量定义
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";  // 手机号正则
    private static final String PASSWORD_PATTERN = "^(?!\\d+$)(?![A-Za-z]+$)[A-Za-z0-9~!@#$%^&*()\\-_=+.,<>?\\|\\[\\]{}:]{8,50}$";  // 密码正则
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";  // 邮箱正则
    private static final String ACCOUNT_PATTERN = "^[a-zA-Z0-9]{1,15}$";  // 账号正则
    private static final String CODE_PATTERN = "^.{1,10}$";  // 验证码正则

    // 验证手机号
    public static void checkPhoneNumber(String phoneNumber) {
        Preconditions.checkArgument(Pattern.matches(PHONE_PATTERN, phoneNumber),
                "手机号格式不正确，应该为11位数字，且以1开头");
    }

    // 验证密码
    public static void checkPassword(String password) {
        Preconditions.checkArgument(Pattern.matches(PASSWORD_PATTERN, password),
                """
                        密码格式不正确：
                        1. 密码长度必须在8到50之间
                        2. 必须包含字母或符号
                        3. 不能是纯数字组合
                        4. 不能包含以下符号~!@#$%^&*()-_=+.,<>?|[]{}:以外的字符""");
    }

    // 验证邮箱
    public static void checkEmail(String email) {
        Preconditions.checkArgument(Pattern.matches(EMAIL_PATTERN, email),
                "邮箱格式不正确，请提供有效的邮箱地址（例如：example@domain.com）");
    }

    // 验证账号
    public static void checkAccount(String account) {
        Preconditions.checkArgument(Pattern.matches(ACCOUNT_PATTERN, account),
                """
                        账号格式不正确：
                        1. 只能包含字母和数字
                        2. 账号长度必须在1到15之间
                        3. 不能包含特殊字符""");
    }

    // 验证验证码
    public static void checkVerifyCode(String code) {
        Preconditions.checkArgument(Pattern.matches(CODE_PATTERN, code),
                "验证码格式不正确：\n" +
                        "验证码长度必须在1到10个字符之间");
    }

    public static void userAccountDtoChecker(UserAccountDto dto){
        Preconditions.checkNotNull(dto,"error,dto is null");
        Preconditions.checkNotNull(dto.getAccount(),"account can not be null!");
        checkAccount(dto.getAccount());
        if(dto.getCreateType() == AccountCreateType.EMAIL){
            Preconditions.checkNotNull(dto.getEmail(),"email can not be null!");
            checkEmail(dto.getEmail());
        }
        if(dto.getCreateType() == AccountCreateType.PHONE){
            Preconditions.checkNotNull(dto.getPhone(),"phone can not be null!");
            checkPhoneNumber(dto.getPhone());
        }
        Preconditions.checkNotNull(dto.getPassword(),"password can not be null!");
        checkPassword(dto.getPassword());
        Preconditions.checkNotNull(dto.getConfirmPassword(),"confirm password can not be null!");
        checkPassword(dto.getConfirmPassword());
        Preconditions.checkNotNull(dto.getVerifyCode(),"verification code can not be null!");
        checkVerifyCode(dto.getVerifyCode());
    }

}
