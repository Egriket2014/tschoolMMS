package com.example.schoolmms.mapper.user;

import com.example.schoolmms.dto.user.UserDto;
import com.example.schoolmms.entity.User;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper extends IMapper<UserDto, User> {

    UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);
}
