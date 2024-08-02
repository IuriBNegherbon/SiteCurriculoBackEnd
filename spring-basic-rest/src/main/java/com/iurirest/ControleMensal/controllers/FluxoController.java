package com.iurirest.ControleMensal.controllers;


import com.iurirest.ControleMensal.dto.FluxoDTO;
import com.iurirest.ControleMensal.dto.FluxoTotalDTO;
import com.iurirest.ControleMensal.models.Fluxo;
import com.iurirest.ControleMensal.repositories.FluxoRepository;
import com.iurirest.ControleMensal.services.FluxoService;
import com.iurirest.crudUser.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fluxo")
@CrossOrigin(origins = "http://localhost:4200")
public class FluxoController {
    private final FluxoRepository fluxoRepository;
    @Autowired
    private FluxoService fluxoService;

    public FluxoController(FluxoRepository fluxoRepository) { this.fluxoRepository = fluxoRepository; }

    @GetMapping
    public ResponseEntity<List<FluxoDTO>> getFluxo(@RequestParam(required = false) String operacao, String agrupador2) {
        List<Fluxo> fluxo;

        if (operacao != null && !operacao.isEmpty()) {
            fluxo = fluxoService.getOperacaoFluxo(operacao);

        } else if (agrupador2 != null && !agrupador2.isEmpty()) {
            fluxo = fluxoService.getAgrupador2Fluxo(agrupador2);

        } else {
            fluxo = fluxoService.getAllFluxo();
        }

        List<FluxoDTO> fluxoDTO = fluxo.stream()
                                    .map(fluxo1 -> new FluxoDTO(fluxo1.getId(), fluxo1.getOperacao(), fluxo1.getDescricao(), fluxo1.getAgrupador1(),
                                                                fluxo1.getAgrupador2(), fluxo1.getData(), fluxo1.getValor()))
                                    .collect(Collectors.toList());

        return new ResponseEntity<>(fluxoDTO, HttpStatus.OK);
    }

    @GetMapping("/total")
    public ResponseEntity<FluxoTotalDTO> getFluxoTotal(@RequestParam String startDate, @RequestParam String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        FluxoTotalDTO summary = fluxoService.getFluxoSummary(start, end);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<FluxoDTO> createFluxo(@Valid @RequestBody Fluxo fluxo) {
        FluxoDTO createdFluxoDTO = fluxoService.createFluxo(fluxo);
        return ResponseEntity.ok(createdFluxoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FluxoDTO> updateFluxo(@PathVariable Long id, @Valid @RequestBody Fluxo fluxo) {
        FluxoDTO updatedFluxoDTO = fluxoService.updateFluxo(id, fluxo);
        return ResponseEntity.ok(updatedFluxoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteFluxo(@PathVariable Long id) {
        Optional<Fluxo> fluxo = fluxoRepository.findById(id);
        if (fluxo.isPresent()) {
            fluxoRepository.deleteById(id);
            return ResponseEntity.ok().body(new ResponseMessage("Registro excluído com sucesso"));
        }
        return ResponseEntity.badRequest().body(new ResponseMessage("Registro não encontrado"));
    }
}
