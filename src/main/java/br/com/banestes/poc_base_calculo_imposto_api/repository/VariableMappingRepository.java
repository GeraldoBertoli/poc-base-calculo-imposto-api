package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.VariableMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "variaveis-mapeamento", collectionResourceRel = "variaveisMapeamento")
public interface VariableMappingRepository extends JpaRepository<VariableMapping, Long> {}