package br.com.banestes.poc_base_calculo_imposto_api.controller;

import br.com.banestes.poc_base_calculo_imposto_api.model.CalculationAudit;
import br.com.banestes.poc_base_calculo_imposto_api.service.CalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calculo")
public class CalculationController {

    private final CalculationService calculationService;

    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/{codBc}")
    public ResponseEntity<CalculationAudit> calcular(@PathVariable String codBc) {
        return ResponseEntity.ok(calculationService.calcularBase(codBc));
    }
    
    @GetMapping("/auditoria")
    public ResponseEntity<List<CalculationAudit>> listarAuditoria() {
        return ResponseEntity.ok(calculationService.getAuditorias());
    }
}
