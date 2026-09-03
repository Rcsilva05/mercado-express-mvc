package br.com.fiap.mercadoexpressmvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidade que representa um produto vendido no Mercado Express
 * (meias, produtos de limpeza, frutas, etc).
 *
 * Mapeia para a tabela TDS_MVC_TB_MERCADO no banco ORACLE_FIAP,
 * o mesmo banco utilizado na Parte 1 (API REST).
 */
@Entity
@Table(name = "TDS_MVC_TB_MERCADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mvc_mercado")
    @SequenceGenerator(name = "seq_mvc_mercado", sequenceName = "SEQ_TDS_MVC_TB_MERCADO", allocationSize = 1)
    private Long id;

    @NotBlank(message = "O nome do produto e obrigatorio")
    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "O tipo do produto e obrigatorio")
    @Column(name = "TIPO", length = 50)
    private String tipo;

    @NotBlank(message = "O setor e obrigatorio")
    @Column(name = "SETOR", length = 50)
    private String setor;

    @Column(name = "TAMANHO", length = 30)
    private String tamanho;

    @NotNull(message = "O preco e obrigatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "O preco nao pode ser negativo")
    @Column(name = "PRECO", precision = 10, scale = 2)
    private BigDecimal preco;
}
