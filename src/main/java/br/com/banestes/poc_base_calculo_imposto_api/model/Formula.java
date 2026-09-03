package br.com.banestes.poc_base_calculo_imposto_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_formula")
@Data
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codBc;
    private String expressaoSpel;
}