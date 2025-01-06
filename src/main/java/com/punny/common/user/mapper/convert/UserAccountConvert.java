package com.punny.common.user.mapper.convert;

import com.punny.common.user.dto.UserAccountDto;
import com.punny.common.user.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserAccountConvert {
    UserAccountConvert INSTANCE = Mappers.getMapper(UserAccountConvert.class);
    UserAccount convertDto(UserAccountDto dto);
}
