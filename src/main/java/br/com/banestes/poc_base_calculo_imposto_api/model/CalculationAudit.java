package br.com.banestes.poc_base_calculo_imposto_api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_auditoria_calculo_item")
@Data
public class CalculationAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codBc;
    @Column(length = 1000)
    private String jsonVariaveisEntrada;
    private BigDecimal valorResultado;
    private LocalDateTime dtProcessamento;
}