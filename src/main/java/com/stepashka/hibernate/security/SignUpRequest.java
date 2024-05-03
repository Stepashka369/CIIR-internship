package com.stepashka.hibernate.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignUpRequest {

    @NotNull(message = "Could not be null")
    @Size(min = 1, max = 25, message = "Size must be between 1 and 25")
    private String firstName;

    @NotNull(message = "Could not be null")
    @Size(min = 1, max = 25, message = "Size must be between 1 and 25")
    private String lastName;

    @NotNull(message = "Could not be null")
    @Email
    private String email;

    @NotNull(message = "Could not be null")
    @Size(min = 1, max = 25, message = "Minimal size 1")
    private String password;

    @NotNull
    @Size(min = 6, max = 6, message = "Size must be 6")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
