package com.iurirest.ControleMensal.repositories;

import com.iurirest.ControleMensal.models.Fluxo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FluxoRepository extends JpaRepository<Fluxo, Long> {
    List<Fluxo> findByOperacao(String operacao);

    @Query("SELECT SUM(f.valor) FROM Fluxo f WHERE f.data BETWEEN :startDate AND :endDate AND f.operacao = :tipo")
    Float sumByPeriodAndType(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("tipo") String tipo);
}
