package com.thalespayments.produto.controller;
import com.thalespayments.produto.dto.ProdutoRequestDTO;
import com.thalespayments.produto.dto.ProdutoResponseDTO;
import com.thalespayments.produto.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@Tag(name = "Produtos", description = "Endpoints para cadastro de produtos PJ")

public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @GetMapping
    @Operation(summary = "Listar todos os produtos")
    public List<ProdutoResponseDTO> listarTodos(){ return produtoService.listarTodos();}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar um novo produto")
    public ProdutoResponseDTO criarProduto(@Valid @RequestBody ProdutoRequestDTO produto){
        return produtoService.criarProduto(produto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto por ID")
    public ProdutoResponseDTO atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoRequestDTO produto){
        return produtoService.atualizarProduto(id, produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletar produto por ID")
    public void deletarProduto(@PathVariable Long id){
        produtoService.deletarProduto(id);
    }

}

