package com.itaupj.produto.exception;

public class ProdutoNotFoundException extends RuntimeException{
    public ProdutoNotFoundException(Long id){
        super("Produto id" + id + "nao encontrado");
    }
}
