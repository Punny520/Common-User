package com.punny.commonuser.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.punny.commonuser.entity.UserAccount;
import com.punny.commonuser.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

/**
* @author Punny
* @description 针对表【user_account(用户账号表，保存用户账以及与账号相关的敏感信息，是用户的站内凭证)】的数据库操作Service实现
* @createDate 2025-01-05 16:22:05
*/
@Service
public class UserAccountService extends ServiceImpl<UserAccountMapper, UserAccount>{

}




