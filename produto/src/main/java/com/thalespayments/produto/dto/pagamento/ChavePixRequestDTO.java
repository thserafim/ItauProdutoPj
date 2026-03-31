package com.thalespayments.produto.dto.pagamento;

import com.thalespayments.produto.model.pagamento.TipoChavePix;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ChavePixRequestDTO {

    @NotNull(message = "Tipo da chave obrigatorio")
    private TipoChavePix tipo;

    @NotBlank(message = "Valor da chave obrigatorio")
    private String valor;

    public TipoChavePix getTipo() {
        return tipo;
    }

    public void setTipo(TipoChavePix tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
