package com.example.schoolmms.dto.user;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String password;
    private List<RoleDto> roleList;
}
