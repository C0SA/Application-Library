package com.example.ApplicationLibrary.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;



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

    public @NotEmpty String getRole() {
        return role;
    }

    public void setRole(@NotEmpty String role) {
        this.role = role;
    }

    public @NotEmpty String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty String username) {
        this.username = username;
    }

    public @NotEmpty String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty String email) {
        this.email = email;
    }

    public @NotEmpty @Size(min = 6, message = "Minimun password length is 6 characters") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty @Size(min = 6, message = "Minimun password length is 6 characters") String password) {
        this.password = password;
    }
}
