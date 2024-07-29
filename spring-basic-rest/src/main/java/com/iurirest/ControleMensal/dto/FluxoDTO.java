package com.iurirest.ControleMensal.dto;

import java.time.LocalDate;

public class FluxoDTO {
    private Long id;
    private String operacao;
    private String descricao;
    private String agrupador1;
    private String agrupador2;
    private LocalDate data;
    private float valor;
    private String message;

    public FluxoDTO() {
    }

    public FluxoDTO(Long id ,String operacao, String descricao, String agrupador1, String agrupador2, LocalDate data, float valor) {
        this.id = id;
        this.operacao = operacao;
        this.descricao = descricao;
        this.agrupador1 = agrupador1;
        this.agrupador2 = agrupador2;
        this.data = data;
        this.valor = valor;
    }

    public FluxoDTO(Long id, String operacao, String descricao, String agrupador1, String agrupador2, LocalDate data, float valor, String message) {
        this.id = id;
        this.operacao = operacao;
        this.descricao = descricao;
        this.agrupador1 = agrupador1;
        this.agrupador2 = agrupador2;
        this.data = data;
        this.valor = valor;
        this.message = message;
    }

    // getters and setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getOperacao() {
        return operacao;
    }
    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAgrupador1() {
        return agrupador1;
    }
    public void setAgrupador1(String agrupador1) {
        this.agrupador1 = agrupador1;
    }

    public String getAgrupador2() {
        return agrupador2;
    }
    public void setAgrupador2(String agrupador2) { this.agrupador2 = agrupador2; }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) { this.data = data; }

    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}