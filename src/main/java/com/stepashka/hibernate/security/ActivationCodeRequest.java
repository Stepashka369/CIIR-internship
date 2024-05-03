package com.stepashka.hibernate.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class ActivationCodeRequest {
    @Email
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
