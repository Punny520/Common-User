package com.punny.common.user.utils;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Preconditions;
import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.entity.UserAccount;
import com.punny.common.user.enums.AccountCreateType;
import com.punny.common.user.service.UserAccountService;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * 参数校验工具类
 */
@Component
public class ArgumentChecker {
    private final UserAccountService userAccountService;

    ArgumentChecker(UserAccountService userAccountService){
        this.userAccountService = userAccountService;
    }
    // 正则表达式常量定义
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";  // 手机号正则
    private static final String PASSWORD_PATTERN = "^(?!\\d+$)(?![A-Za-z]+$)[A-Za-z0-9~!@#$%^&*()\\-_=+.,<>?\\|\\[\\]{}:]{8,50}$";  // 密码正则
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";  // 邮箱正则
    private static final String ACCOUNT_PATTERN = "^[a-zA-Z0-9]{1,15}$";  // 账号正则
    private static final String CODE_PATTERN = "^\\d{6}$";  // 验证码正则

    // 验证手机号格式
    public void checkPhoneNumberPattern(String phoneNumber) {
        Preconditions.checkArgument(ObjectUtil.isNotNull(phoneNumber)&&Pattern.matches(PHONE_PATTERN, phoneNumber),
                "手机号格式不正确，应该为11位数字，且以1开头");
    }

    // 验证密码格式
    public void checkPasswordPattern(String password) {
        Preconditions.checkArgument(ObjectUtil.isNotNull(password)&&Pattern.matches(PASSWORD_PATTERN, password),
                """
                        密码格式不正确：
                        1. 密码长度必须在8到50之间
                        2. 必须包含字母或符号
                        3. 不能是纯数字组合
                        4. 不能包含以下符号~!@#$%^&*()-_=+.,<>?|[]{}:以外的字符""");
    }

    // 验证邮箱格式
    public void checkEmailPattern(String email) {
        Preconditions.checkArgument(ObjectUtil.isNotNull(email)&&Pattern.matches(EMAIL_PATTERN, email),
                "邮箱格式不正确，请提供有效的邮箱地址（例如：example@domain.com）");
    }

    // 验证账号格式
    public void checkAccountPattern(String account) {
        Preconditions.checkArgument(ObjectUtil.isNotNull(account)&&Pattern.matches(ACCOUNT_PATTERN, account),
                """
                        账号格式不正确：
                        1. 只能包含字母和数字
                        2. 账号长度必须在1到15之间
                        3. 不能包含特殊字符""");
    }

    // 验证验证码格式
    public void checkVerifyCodePattern(String code) {
        Preconditions.checkArgument(ObjectUtil.isNotNull(code)&&Pattern.matches(CODE_PATTERN, code),
                "验证码格式不正确：\n" +
                        "请输入6位数字验证码");
    }

    private void checkAccountExist(UserAccountDto userAccountDto){
        checkAccountPattern(userAccountDto.getAccount());
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", userAccountDto.getAccount());
        long count = userAccountService.count(queryWrapper);
        if(count != 0){
            throw new IllegalArgumentException("该账号已经注册");
        }
    }

    private void checkPhoneExist(UserAccountDto userAccountDto){
        checkPhoneNumberPattern(userAccountDto.getPhone());
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", userAccountDto.getPhone());
        long count = userAccountService.count(queryWrapper);
        if(count != 0){
            throw new IllegalArgumentException("该手机号已经被注册");
        }
    }

    private void checkEmailExist(UserAccountDto userAccountDto){
        checkEmailPattern(userAccountDto.getEmail());
        QueryWrapper<UserAccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userAccountDto.getEmail());
        long count = userAccountService.count(queryWrapper);
        if(count != 0){
            throw new IllegalArgumentException("该邮箱号已经被注册");
        }
    }
    /**
     * 检查内容包含：
     * 参数格式
     * 数据库
     * @param dto 前端数据
     */
    public void userAccountDtoCheckBeforeCreate(UserAccountDto dto){
        checkAccountExist(dto);
        if(dto.getCreateType() == AccountCreateType.PHONE){
            checkPhoneExist(dto);
        }
        if(dto.getCreateType() == AccountCreateType.EMAIL){
            checkEmailExist(dto);
        }
        checkPasswordPattern(dto.getPassword());
    }

    /**
     * 验证码校验
     * @param currentValue 用户传入的验证码
     * @param exceptValue 预期的验证码
     */
    public void verifyCodeVerification(String currentValue,String exceptValue) {
        checkVerifyCodePattern(currentValue);
        Preconditions.checkNotNull(exceptValue,"验证码已过期,请重新获取");
        Preconditions.checkArgument(exceptValue.equals(currentValue),"验证码错误");
    }

}
