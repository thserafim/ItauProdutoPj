package com.itaupj.produto.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class ProdutoPj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome Obrigatório") //Evita strings sem preencimento
    private String nome;

    @NotNull(message = "Necessário Preco")
    @DecimalMin(value = "0.0", inclusive = true, message = "Preco negativo") //ação que previne erro com valores negativos
    @Column(precision = 15, scale = 2)
    private BigDecimal preco;

    @NotBlank(message = "Descricao obrigatória")
    private String descricao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
