package com.thalespayments.produto.exception.pagamento;

public class ChavePixNotFoundException extends RuntimeException {

    public ChavePixNotFoundException(Long id) {
        super("Chave Pix com id " + id + " nao encontrada");
    }
}
