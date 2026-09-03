package br.com.banestes.poc_base_calculo_imposto_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_variavel_mapeamento")
@Data
public class VariableMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeVariavel; 
    private String operacao;
    
    @ManyToOne
    private TaxCode codTrib;
}