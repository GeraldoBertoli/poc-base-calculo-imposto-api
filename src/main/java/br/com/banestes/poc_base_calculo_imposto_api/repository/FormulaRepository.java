package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.Formula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormulaRepository extends JpaRepository<Formula, Long> {
    Formula findByCodBc(String codBc);
}