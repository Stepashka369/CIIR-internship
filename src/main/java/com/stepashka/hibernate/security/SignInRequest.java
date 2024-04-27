package com.stepashka.hibernate.security;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignInRequest {

    @NotNull(message = "Could not be null")
    @Size(min = 1, max = 12, message = "Size must be between 1 and 12")
    private String phoneNumber;

    @NotNull(message = "Could not be null")
    @Size(min = 1, max = 25, message = "Minimal size 1")
    private String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
