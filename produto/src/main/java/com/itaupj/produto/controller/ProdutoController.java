package com.itaupj.produto.controller;
import com.itaupj.produto.model.ProdutoPj;
import com.itaupj.produto.repository.ProdutoRepository;
import com.itaupj.produto.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")

public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping
    public List<ProdutoPj> listarTodos(){ return produtoService.lstarTodos();}

    @PostMapping
    public ProdutoPj criarProduto(@Valid @RequestBody ProdutoPj produto){
        return produtoService.criarProduto(produto);
    }
    @GetMapping("/{id}")
    public ProdutoPj buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public ProdutoPj atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoPj produto){
        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
    }

}
