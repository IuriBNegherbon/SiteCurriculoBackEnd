package com.iurirest.ControleMensal.dto;

import java.math.BigDecimal;

public class FluxoTotalDTO {
    private BigDecimal totalEntrada;
    private BigDecimal totalSaida;
    private BigDecimal totalDiferenca;

    public FluxoTotalDTO() {}

    public FluxoTotalDTO(BigDecimal totalentrada, BigDecimal  totalSaida) {
        this.totalEntrada = totalentrada;
        this.totalSaida = totalSaida;
        this.totalDiferenca = totalEntrada.subtract(totalSaida);
    }

    public BigDecimal getTotalEntrada() {
        return totalEntrada;
    }

    public void setTotalEntrada(BigDecimal totalEntrada) {
        this.totalEntrada = totalEntrada;
    }

    public BigDecimal getTotalSaida() { return totalSaida; }

    public void setTotalSaida(BigDecimal totalSaida) {
        this.totalSaida = totalSaida;
    }

    public BigDecimal getTotalDiferenca() { return totalDiferenca; }

    public void setTotalDiferenca(BigDecimal totalDiferenca) {
        this.totalDiferenca = totalDiferenca;
    }
}

