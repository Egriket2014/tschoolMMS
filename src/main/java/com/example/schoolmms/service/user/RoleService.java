package com.example.schoolmms.service.user;

import com.example.schoolmms.dto.user.RoleDto;
import com.example.schoolmms.entity.Role;
import com.example.schoolmms.mapper.user.RoleMapper;
import com.example.schoolmms.repository.user.RoleRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepositoryImpl roleRepository;
    private final RoleMapper roleMapper;

    public long getNumberOfEntity() {
        return roleRepository.count();
    }

    public List<RoleDto> getAllRole() {
        return roleMapper.toDtoList(roleRepository.findAll());
    }

    public RoleDto getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDto)
                .orElse(null);
    }

    public RoleDto getRoleByName(String name) {
        return roleRepository.findByName(name)
                .map(roleMapper::toDto)
                .orElse(null);
    }

    public Role getEntityByName(String name) {
        return roleRepository.findByName(name)
                .orElse(null);
    }

    @Transactional
    public void addRole(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());
        roleRepository.save(role);
    }

    @Transactional
    public void updateRole(Long id) {
        roleRepository.findById(id).ifPresent(roleRepository::update);
    }
}
