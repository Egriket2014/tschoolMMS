package com.example.schoolmms.mapper.user;

import com.example.schoolmms.dto.user.RoleDto;
import com.example.schoolmms.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper ROLE_MAPPER_INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);

    Set<RoleDto> toDtoSet(Set<Role> roleSet);

    Role toEntity(RoleDto roleDto);

    Set<Role> toEntitySet(Set<RoleDto> roleDtoSet);
}
