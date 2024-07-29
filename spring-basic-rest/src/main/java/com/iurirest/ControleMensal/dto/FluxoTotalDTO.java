package com.iurirest.ControleMensal.dto;

public class FluxoTotalDTO {
    private float totalEntrada;
    private float totalSaida;
    private float totalDiferenca;

    public FluxoTotalDTO() {}

    public FluxoTotalDTO(float totalentrada, float totalSaida) {
        this.totalEntrada = totalentrada;
        this.totalSaida = totalSaida;
        this.totalDiferenca = totalentrada - totalSaida;
    }

    public float getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(float totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public float getTotalSaida() {
        return totalSaida;
    }

    public void setTotalSaida(float totalSaida) {
        this.totalSaida = totalSaida;
    }

    public float getTotalDiferenca() {
        return totalDiferenca;
    }

    public void setTotalDiferenca(float totalDiferenca) {
        this.totalDiferenca = totalDiferenca;
    }
}

