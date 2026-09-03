package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.CalculationAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationAuditRepository extends JpaRepository<CalculationAudit, Long> {}