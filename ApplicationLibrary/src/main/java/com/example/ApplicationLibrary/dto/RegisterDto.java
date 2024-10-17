package com.example.ApplicationLibrary.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterDto {

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;


    @NotEmpty
    @Size(min = 6, message = "Minimun password length is 6 characters")
    private String password;

    @NotEmpty
    private String role;

}
