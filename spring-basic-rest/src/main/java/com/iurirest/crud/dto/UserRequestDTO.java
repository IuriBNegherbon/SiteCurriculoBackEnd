package com.iurirest.crud.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDTO {
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @NotNull
    @Size(min = 10, max = 50)
    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getpassword() {
        return password;
    }

}
