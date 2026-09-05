package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.GlobalVariable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "global-variables")
public interface GlobalVariableRepository extends JpaRepository<GlobalVariable, Long> {
}