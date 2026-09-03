package br.com.banestes.poc_base_calculo_imposto_api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_cod_trib")
@Data
public class TaxCode {
    @Id
    private String codigo;
    private String descricao;
}