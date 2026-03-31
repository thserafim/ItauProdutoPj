package com.thalespayments.produto.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProdutoRequestDTO {

    @NotBlank(message = "Nome obrigatorio")
    private String nome;

    @NotNull(message = "Preco obrigatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "Preco nao pode ser negativo")
    private BigDecimal preco;

    @NotBlank(message = "Descricao obrigatoria")
    private String descricao;

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public BigDecimal getPreco(){
        return preco;
    }

    public void setPreco(BigDecimal preco){
        this.preco = preco;
    }

    public String getDescricao(){
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }


}
