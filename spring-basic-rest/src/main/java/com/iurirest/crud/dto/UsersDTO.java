package com.iurirest.crud.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsersDTO {
    private Long id;
    private String name;
    private String email;
    private String message;
    private String cpf;

    public UsersDTO() {
    }

    public UsersDTO(Long id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public UsersDTO(Long id, String name, String email, String cpf, String message) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.message = message;
        this.cpf = cpf;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String email) {
        this.cpf = cpf;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
