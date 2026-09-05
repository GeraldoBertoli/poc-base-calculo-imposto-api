package br.com.banestes.poc_base_calculo_imposto_api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_variavel_global")
@Data 
public class GlobalVariable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String name; // Ex: pIBSMun
    
    @Column(name = "valor", nullable = false, precision = 19, scale = 4)
    private BigDecimal value; // Ex: 2.0000

    // Getters e Setters (ou use @Data do Lombok)
    public String getName() { return name; }
    public BigDecimal getValue() { return value; }
}