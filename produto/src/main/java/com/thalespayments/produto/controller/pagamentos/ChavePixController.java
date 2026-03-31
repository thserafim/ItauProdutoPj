package com.thalespayments.produto.controller.pagamentos;

import com.thalespayments.produto.dto.pagamento.ChavePixRequestDTO;
import com.thalespayments.produto.dto.pagamento.ChavePixResponseDTO;
import com.thalespayments.produto.service.pagamento.ChavePixService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chaves")
@Tag(name = "Chaves Pix", description = "Cadastro simples de chaves Pix")
public class ChavePixController {

    private final ChavePixService chavePixService;

    public ChavePixController(ChavePixService chavePixService) {
        this.chavePixService = chavePixService;
    }

    @GetMapping
    @Operation(summary = "Listar chaves Pix")
    public List<ChavePixResponseDTO> listarTodas() {
        return chavePixService.listarTodas();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar chave Pix por ID")
    public ChavePixResponseDTO buscarPorId(@PathVariable Long id) {
        return chavePixService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cadastrar chave Pix")
    public ChavePixResponseDTO cadastrar(@Valid @RequestBody ChavePixRequestDTO requestDTO) {
        return chavePixService.cadastrar(requestDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar chave Pix")
    public ChavePixResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody ChavePixRequestDTO requestDTO) {
        return chavePixService.atualizar(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remover chave Pix")
    public void remover(@PathVariable Long id) {
        chavePixService.remover(id);
    }
}
