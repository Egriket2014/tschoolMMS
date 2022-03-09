package com.example.schoolmms.service.user;

import com.example.schoolmms.dto.user.UserDto;
import com.example.schoolmms.entity.User;
import com.example.schoolmms.mapper.user.RoleMapper;
import com.example.schoolmms.mapper.user.UserMapper;
import com.example.schoolmms.repository.user.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    public long getNumberOfEntity() {
        return userRepository.count();
    }

    public List<UserDto> getAllUser() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @Transactional
    public void addUser(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .dateOfBirth(userDto.getDateOfBirth())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .addressList(new ArrayList<>())
                .orderList(new ArrayList<>())
                .roleList(roleMapper.toEntityList(userDto.getRoleList()))
                .build();

        userRepository.save(user);
    }

    @Transactional
    public void updateUser(Long id) {
        userRepository.findById(id).ifPresent(userRepository::update);
    }


    public UserDto getByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElse(null));
    }
}
