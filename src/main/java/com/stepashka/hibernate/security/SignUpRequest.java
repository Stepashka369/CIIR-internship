package com.stepashka.hibernate.security;

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
    @Size(min = 1, max = 12, message = "Size must be between 1 and 12")
    private String phoneNumber;

   @NotNull(message = "Could not be null")
   @Size(min = 1, max = 25, message = "Minimal size 1")
    private String password;

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
