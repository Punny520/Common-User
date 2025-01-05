package com.punny.commonuser.dto;

import com.punny.commonuser.enums.AccountCreateType;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserAccountDto implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String confirmPassword;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 注册方式
     */
    private AccountCreateType createType;
    /**
     * 验证码
     */
    private String verifyCode;

    private static final long serialVersionUID = 1L;

    public void setCreateType(int typeKey){
        this.createType = AccountCreateType.getTypeByKey(typeKey);
    }

    public void setCreateType(AccountCreateType createType){
        this.createType = createType;
    }
}