package com.iurirest.ControleMensal.services;

import com.iurirest.ControleMensal.dto.FluxoDTO;
import com.iurirest.ControleMensal.dto.FluxoTotalDTO;
import com.iurirest.ControleMensal.models.Fluxo;
import com.iurirest.ControleMensal.repositories.FluxoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FluxoService {

    private final FluxoRepository fluxoRepository;

    @Autowired
    public FluxoService(FluxoRepository fluxoRepository) { this.fluxoRepository = fluxoRepository; }

    public List<Fluxo> getAllFluxo() {return fluxoRepository.findAll();}
    public List<Fluxo> getOperacaoFluxo(String operacao) {return fluxoRepository.findByOperacao(operacao);}
    public List<Fluxo> getAgrupador2Fluxo(String agrupador2) {return fluxoRepository.findByAgrupador2(agrupador2);}

    public FluxoDTO updateFluxo(Long id, Fluxo fluxo) {

        Optional<Fluxo> fluxoToUpdate = fluxoRepository.findById(id);

        Fluxo updatedFluxo = fluxoToUpdate.get();
        updatedFluxo.setOperacao(fluxo.getOperacao());
        updatedFluxo.setDescricao(fluxo.getDescricao());
        updatedFluxo.setAgrupador1(fluxo.getAgrupador1());
        updatedFluxo.setAgrupador2(fluxo.getAgrupador2());
        updatedFluxo.setData(fluxo.getData());
        updatedFluxo.setValor(fluxo.getValor());
        Fluxo savedFluxo = fluxoRepository.save(updatedFluxo);

        return new FluxoDTO(savedFluxo.getId(), savedFluxo.getOperacao(), savedFluxo.getDescricao(), savedFluxo.getAgrupador1()
                , savedFluxo.getAgrupador2(), savedFluxo.getData(), savedFluxo.getValor(), "Registro alterado com sucesso");
    }

    public FluxoDTO createFluxo(Fluxo fluxo) {
        Fluxo createdFluxo = fluxoRepository.save(fluxo);
        return new FluxoDTO(createdFluxo.getId(), createdFluxo.getOperacao(), createdFluxo.getDescricao(), createdFluxo.getAgrupador1()
                , createdFluxo.getAgrupador2(), createdFluxo.getData(), createdFluxo.getValor(), "Registro inserido com sucesso");
    }

    private BigDecimal getSumByPeriodAndType(LocalDate startDate, LocalDate endDate, String tipo) {
        return Optional.ofNullable(fluxoRepository.sumByPeriodAndType(startDate, endDate, tipo)).orElse(BigDecimal.ZERO);
    }

    public FluxoTotalDTO getFluxoSummary(LocalDate startDate, LocalDate endDate) {
        BigDecimal totalEntrada = getSumByPeriodAndType(startDate, endDate, "entrada");
        BigDecimal totalSaida = getSumByPeriodAndType(startDate, endDate, "saida");
        return new FluxoTotalDTO(totalEntrada, totalSaida);
    }

    public List<Fluxo> getAllByData(LocalDate startDate, LocalDate endDate) {
        return fluxoRepository.findByDataBetween(startDate, endDate);
    }
}
