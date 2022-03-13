package com.example.schoolmms.dto.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
}
