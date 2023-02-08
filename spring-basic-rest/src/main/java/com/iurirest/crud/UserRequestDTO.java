package com.iurirest.crud;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRequestDTO {
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    @NotNull
    @Size(min = 10, max = 50)
    //@Email
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
