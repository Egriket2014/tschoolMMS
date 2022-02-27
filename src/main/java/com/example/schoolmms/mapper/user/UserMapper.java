package com.example.schoolmms.mapper.user;

import com.example.schoolmms.dto.user.UserDto;
import com.example.schoolmms.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {

    UserMapper USER_MAPPER_INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.roleSet", target = "roleDtoSet")
    UserDto toDto(User user);

    @Mapping(source = "user.roleSet", target = "roleDtoSet")
    List<UserDto> toDtoList(List<User> userList);

    @Mapping(source = "userDto.roleDtoSet", target = "roleSet")
    User toEntity(UserDto userDto);
}
