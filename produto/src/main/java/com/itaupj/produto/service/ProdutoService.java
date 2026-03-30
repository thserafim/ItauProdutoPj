package com.itaupj.produto.service;

import com.itaupj.produto.exception.ProdutoNotFoundException;
import com.itaupj.produto.model.ProdutoPj;
import com.itaupj.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){ //construtor
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoPj> lstarTodos(){
        return produtoRepository.findAll();
    }

    public ProdutoPj buscarPorId(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
    }

    public ProdutoPj criarProduto(ProdutoPj produto){
        return produtoRepository.save(produto);
    }

    public ProdutoPj atualizarProduto(Long id, ProdutoPj produtoAtualizado){
        ProdutoPj existente = buscarPorId(id);
        existente.setNome(produtoAtualizado.getNome());
        existente.setPreco(produtoAtualizado.getPreco());
        existente.setDescricao(produtoAtualizado.getDescricao());
        return produtoRepository.save(existente);
    }

    public void deletarProduto(Long id){
        ProdutoPj existente = buscarPorId(id);
        produtoRepository.delete(existente);
    }

}
