package com.thalespayments.produto.dto.pagamento;

import com.thalespayments.produto.model.pagamento.TipoChavePix;

public class ChavePixResponseDTO {

    private Long id;
    private TipoChavePix tipo;
    private String valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
