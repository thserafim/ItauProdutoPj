package com.itaupj.produto.repository;

import com.itaupj.produto.model.ProdutoPj;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest

public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test //Realizando o teste de salvamento do produto
    public void salvarProduto_retornoProdutoSalvo(){
        ProdutoPj produto = new ProdutoPj();
        produto.setNome("Credito para PJ");
        produto.setPreco(2.000);
        produto.setDescricao("Produto destinado a pequenas empresas");

        ProdutoPj produtoSalvo = produtoRepository.save(produto);
        Assertions.assertNotNull(produtoSalvo.getId());
        Assertions.assertEquals("Credito para PJ", produtoSalvo.getNome());

    }

    @Test //Teste para busca de produto
    public void buscarProdutoId_retornoProdutoPorId(){

        ProdutoPj produto = new ProdutoPj();
        produto.setNome("Credito para PJ");
        produto.setPreco(2.000);
        produto.setDescricao("Produto destinado a pequenas empresas");

        ProdutoPj produtoSalvo = produtoRepository.save(produto);
        Optional<ProdutoPj> produtoEncontrado = produtoRepository.findById(produtoSalvo.getId());
        Assertions.assertTrue(produtoEncontrado.isPresent());
        Assertions.assertEquals(produtoSalvo.getId(), produtoEncontrado.get().getId());
    }

    @Test //Teste atualizando um produto
    public void atualizandoProduto_AtualizarNomeDoProduto(){
        ProdutoPj produto = new ProdutoPj();
        produto.setNome("Maquina de Cartao");
        produto.setPreco(250.00);
        produto.setDescricao("Maquinas para pequenas empresas");

        ProdutoPj produtoSalvo = produtoRepository.save(produto);
        produtoSalvo.setNome("Produto Atualizado");
        ProdutoPj produtoAtualizado = produtoRepository.save(produtoSalvo);

        Assertions.assertEquals("Produto Atualizado", produtoAtualizado.getNome());
    }



}
