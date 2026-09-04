package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "formulas", collectionResourceRel = "formulas")
public interface FormulaRepository extends JpaRepository<Formula, Long> {
    Formula findByCodBc(String codBc);
}