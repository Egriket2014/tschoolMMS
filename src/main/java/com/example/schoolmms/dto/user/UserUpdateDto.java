package com.example.schoolmms.dto.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@Data
public class UserUpdateDto {

    private Long id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 50)
    private String name;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 50)
    private String surname;

    @NotEmpty(message = "Date of birth should not be empty")
    private String dateOfBirth;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;
}
