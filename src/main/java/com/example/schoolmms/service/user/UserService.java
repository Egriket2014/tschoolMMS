package com.example.schoolmms.service.user;

import com.example.schoolmms.dto.user.UserDto;
import com.example.schoolmms.dto.user.UserRegistrationDto;
import com.example.schoolmms.dto.user.UserUpdateDto;
import com.example.schoolmms.entity.Role;
import com.example.schoolmms.entity.User;
import com.example.schoolmms.mapper.user.RoleMapper;
import com.example.schoolmms.mapper.user.UserMapper;
import com.example.schoolmms.repository.user.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositoryImpl userRepository;
    private final UserMapper userMapper;

    private final RoleMapper roleMapper;
    private final RoleService roleService;

    private final PasswordEncoder bCryptPasswordEncoder;

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

    private Date stringToDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(s);
    }

    private UserUpdateDto getUserUpdateDto(UserDto userDto) {
        if (userDto == null) return null;
        return UserUpdateDto.builder()
                        .id(userDto.getId())
                        .name(userDto.getName())
                        .surname(userDto.getSurname())
                        .dateOfBirth(userDto.getDateOfBirth().toString())
                        .email(userDto.getEmail())
                        .build();
    }

    @Transactional
    public void registerUser(UserRegistrationDto userDto) {
        try {
            List<Role> roles = new ArrayList<>();
            roles.add(roleService.getEntityByName("ROLE_USER"));

            User user = User.builder()
                    .name(userDto.getName())
                    .surname(userDto.getSurname())
                    .dateOfBirth(stringToDate(userDto.getDateOfBirth()))
                    .email(userDto.getEmail())
                    .password(bCryptPasswordEncoder.encode(userDto.getPassword()))
                    .roleList(roles)
                    .build();

            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("User registration error"); // Maybe add logging in future
        }
    }

    @Transactional
    public String registerNewUserController(UserRegistrationDto userRegistrationDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (getByEmail(userRegistrationDto.getEmail()) != null) {
            model.addAttribute("userError", "User with this email is already registered");
            return "registration";
        }

        registerUser(userRegistrationDto);
        return "redirect:/";
    }

    public void getUserPageController(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = getByEmail(authentication.getName());
        model.addAttribute("userDto", userDto);
    }

    public void getEditUserPageController(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        UserDto userDto = getByEmail(currentUser);
        model.addAttribute("userUpdateDto", getUserUpdateDto(userDto));
    }

    @Transactional
    public String editUserController(UserUpdateDto userUpdateDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "user-edit";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        if (!currentUser.equals(userUpdateDto.getEmail()) && getByEmail(userUpdateDto.getEmail()) != null) {
            model.addAttribute("userUpdateDto", userUpdateDto);
            model.addAttribute("userError", "User with this email is already registered");
            return "user-edit";
        }
        updateUser(userUpdateDto);
        return "redirect:/user";
    }

    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        try {
            User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(() ->
                    new UsernameNotFoundException("User with id = " + userUpdateDto.getId() + "not found"));

            user.setName(userUpdateDto.getName());
            user.setSurname(userUpdateDto.getSurname());
            user.setDateOfBirth(stringToDate(userUpdateDto.getDateOfBirth()));
            user.setEmail(userUpdateDto.getEmail());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user.getEmail(),
                    user.getPassword(),
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            userRepository.update(user);
        } catch (Exception e) {
            System.out.println("User registration error " + e); // Maybe add logging in future
        }
    }

    public UserDto getByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElse(null));
    }
}
