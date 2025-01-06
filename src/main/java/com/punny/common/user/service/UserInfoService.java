package com.punny.common.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.punny.common.user.entity.UserInfo;
import com.punny.common.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author Punny
* @description 针对表【user_info(用户的账号的非敏感附属信息，比如昵称等)】的数据库操作Service实现
* @createDate 2025-01-05 16:22:05
*/
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

}




