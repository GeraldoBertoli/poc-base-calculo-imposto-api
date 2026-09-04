package br.com.banestes.poc_base_calculo_imposto_api.config;

import br.com.banestes.poc_base_calculo_imposto_api.model.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        // Exibe explicitamente a propriedade 'id' no JSON serializado
        config.exposeIdsFor(
            Formula.class,
            VariableMapping.class,
            AccountBalance.class,
            TaxCode.class,
            CalculationAudit.class
        );
    }
}