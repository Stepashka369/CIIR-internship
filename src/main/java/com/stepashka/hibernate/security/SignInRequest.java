package com.stepashka.hibernate.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignInRequest {

    @NotNull(message = "Could not be null")
    @Email
    private String email;

    @NotNull(message = "Could not be null")
    @Size(min = 1, max = 25, message = "Minimal size 1")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
