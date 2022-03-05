package com.example.schoolmms.mapper.user;

import com.example.schoolmms.dto.user.RoleDto;
import com.example.schoolmms.entity.Role;
import com.example.schoolmms.mapper.IMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper extends IMapper<RoleDto, Role> {

    RoleMapper ROLE_MAPPER_INSTANCE = Mappers.getMapper(RoleMapper.class);
}
