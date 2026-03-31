package com.thalespayments.produto.service;

import com.thalespayments.produto.dto.ProdutoRequestDTO;
import com.thalespayments.produto.dto.ProdutoResponseDTO;
import com.thalespayments.produto.exception.ProdutoNotFoundException;
import com.thalespayments.produto.model.ProdutoPj;
import com.thalespayments.produto.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){ //construtor
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoResponseDTO> listarTodos(){
        return produtoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ProdutoResponseDTO buscarPorId(Long id){
        ProdutoPj produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        return toResponseDTO(produto);
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoDTO){
        ProdutoPj produto = new ProdutoPj();
        produto.setNome(produtoDTO.getNome());
        produto.setPreco(produtoDTO.getPreco());
        produto.setDescricao(produtoDTO.getDescricao());

        ProdutoPj salvo = produtoRepository.save(produto);
        return toResponseDTO(salvo);
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoAtualizadoDTO){
        ProdutoPj existente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));

        existente.setNome(produtoAtualizadoDTO.getNome());
        existente.setPreco(produtoAtualizadoDTO.getPreco());
        existente.setDescricao(produtoAtualizadoDTO.getDescricao());
        ProdutoPj atualizado = produtoRepository.save(existente);

        return toResponseDTO(atualizado);
    }

    public void deletarProduto(Long id){
        ProdutoPj existente = produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNotFoundException(id));
        produtoRepository.delete(existente);
    }

    private ProdutoResponseDTO toResponseDTO(ProdutoPj produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPreco());
        dto.setDescricao(produto.getDescricao());
        return dto;
    }

}
