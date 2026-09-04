package br.com.banestes.poc_base_calculo_imposto_api.repository;

import br.com.banestes.poc_base_calculo_imposto_api.model.TaxCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "codigos-tributacao", collectionResourceRel = "codigosTributacao")
public interface TaxCodeRepository extends JpaRepository<TaxCode, String> {}