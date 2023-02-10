package com.iurirest.crud.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.iurirest.crud.utils.CPF;
//import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Scanner;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @JsonProperty("name")
    @NotNull
    @Size(min = 4, max = 50)
    private String name;
    @Column
    @JsonProperty("email")
    @NotNull
    @Size(min = 10, max = 50)
    @Email
    private String email;
    @Column
    @JsonProperty("password")
    @NotNull
    @Size(min = 6)
    private String password;
    @Column(unique = true)
    @JsonProperty("cpf")
    @NotNull
    @Size(max = 11)
    @CPF
    private String cpf;


    public Users() {
    }

    public Users(String name, String email, String password, String cpf) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpf = cpf;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setMessage(String usuárioNãoEncontrado) {
    }

}
