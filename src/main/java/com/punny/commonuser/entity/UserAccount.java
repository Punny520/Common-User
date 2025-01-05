package com.punny.commonuser.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;

/**
 * 用户账号表，保存用户账以及与账号相关的敏感信息，是用户的站内凭证
 * @TableName user_account
 */
@TableName(value ="user_account")
@Data
public class UserAccount implements Serializable {
    /**
     * 主键
     */
    @TableId
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
     * 手机号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 删除标记
     */
    @TableLogic
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}