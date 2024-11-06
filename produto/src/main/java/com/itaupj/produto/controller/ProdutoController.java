package com.itaupj.produto.controller;
import com.itaupj.produto.model.ProdutoPj;
import com.itaupj.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoPj> listarTodos(){   //MEU READ - LEITURA DE TODOS OS PRODUTOS
        return produtoRepository.findAll(); //findall??
    }

    @PostMapping
    public ProdutoPj criarProduto(@RequestBody ProdutoPj produto){
        return produtoRepository.save(produto);
    }
    @GetMapping("/{id}")
    public ProdutoPj buscarPorId(@PathVariable Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto nao encontrado!"));
    }

    @PutMapping("/{id}")
    public ProdutoPj atualizarProduto(@PathVariable Long id, @RequestBody ProdutoPj produto){
        ProdutoPj existente = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto nao encontrado"));
        existente.setNome(produto.getNome());
        existente.setPreco(produto.getPreco());
        existente.setDescricao(produto.getDescricao());
        return produtoRepository.save(existente);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id){
        produtoRepository.deleteById(id);
    }

}
