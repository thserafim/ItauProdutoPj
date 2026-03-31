package com.thalespayments.produto.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(Long id){
        super("Produto com id " + id + " nao encontrado");
    }
}
