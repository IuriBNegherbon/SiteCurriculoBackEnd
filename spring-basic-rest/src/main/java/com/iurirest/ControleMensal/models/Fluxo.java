package com.iurirest.ControleMensal.models;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Fluxo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @JsonProperty("operacao")
    @NotNull
    @Size(min = 4, max = 50)
    private String operacao;
    @Column
    @JsonProperty("descricao")
    @Size(max = 500)
    private String descricao;
    @Column
    @JsonProperty("agrupador1")
    @Size(min = 4, max = 50)
    private String agrupador1;
    @Column
    @JsonProperty("agrupador2")
    @Size(min = 4, max = 50)
    private String agrupador2;
    @Column
    @JsonProperty("data")
    @NotNull
    private LocalDate data;
    @Column
    @JsonProperty("valor")
    @NotNull
    private float valor;


    public Fluxo() {
    }

    public Fluxo(String operacao, String descricao, String agrupador1, String agrupador2, LocalDate data, float valor) {
        this.operacao = operacao;
        this.descricao = descricao;
        this.agrupador1 = agrupador1;
        this.agrupador2 = agrupador2;
        this.data = data;
        this.valor = valor;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setAgrupador1(String agrupador1) {
        this.agrupador1 = agrupador1;
    }

    public void setAgrupador2(String agrupador2) {
        this.agrupador2 = agrupador2;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getOperacao() {
        return operacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getAgrupador1() {
        return agrupador1;
    }

    public String getAgrupador2() {
        return agrupador2;
    }

    public LocalDate getData() {
        return data;
    }

    public float getValor() {
        return valor;
    }

}
