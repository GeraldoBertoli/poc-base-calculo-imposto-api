package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.VariableMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariableMappingRepository extends JpaRepository<VariableMapping, Long> {}