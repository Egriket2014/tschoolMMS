package com.example.schoolmms.controllers;

import com.example.schoolmms.dto.user.UserDto;
import com.example.schoolmms.dto.user.UserRegistrationDto;
import com.example.schoolmms.dto.user.UserUpdateDto;
import com.example.schoolmms.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               Model model) {
        model.addAttribute("error", error != null);
        return "login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegDto", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registerNewUser(@ModelAttribute("userRegDto") @Valid UserRegistrationDto userRegistrationDto,
                                  BindingResult bindingResult,
                                  Model model) {
        return userService.registerNewUserController(userRegistrationDto, bindingResult, model);
    }

    @GetMapping("/user")
    public String getUserPage(Model model) {
        userService.getUserPageController(model);
        return "user";
    }

    @GetMapping("/user/edit")
    public String getEditUserPage(Model model) {
        userService.getEditUserPageController(model);
        return "user-edit";
    }

    @PostMapping("user/edit")
    public String editUser(@ModelAttribute("userForm") @Valid UserUpdateDto userUpdateDto,
                                 BindingResult bindingResult,
                                 Model model) {
        return userService.editUserController(userUpdateDto, bindingResult, model);
    }

}
