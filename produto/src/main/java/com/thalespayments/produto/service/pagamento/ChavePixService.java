package com.thalespayments.produto.service.pagamento;

import com.thalespayments.produto.dto.pagamento.ChavePixRequestDTO;
import com.thalespayments.produto.dto.pagamento.ChavePixResponseDTO;
import com.thalespayments.produto.exception.pagamento.ChavePixNotFoundException;
import com.thalespayments.produto.model.pagamento.ChavePix;
import com.thalespayments.produto.model.pagamento.TipoChavePix;
import com.thalespayments.produto.repository.pagamento.ChavePixRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ChavePixService {

    private static final String DUPLICATE_KEY_MESSAGE = "Ja existe uma chave Pix com esse valor";
    private final ChavePixRepository chavePixRepository;

    public ChavePixService(ChavePixRepository chavePixRepository) {
        this.chavePixRepository = chavePixRepository;
    }

    public List<ChavePixResponseDTO> listarTodas() {
        return chavePixRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public ChavePixResponseDTO buscarPorId(Long id) {
        ChavePix chavePix = chavePixRepository.findById(id)
                .orElseThrow(() -> new ChavePixNotFoundException(id));
        return toResponseDTO(chavePix);
    }

    public ChavePixResponseDTO cadastrar(ChavePixRequestDTO requestDTO) {
        String valorNormalizado = normalizarValor(requestDTO.getTipo(), requestDTO.getValor());
        validarFormatoPorTipo(requestDTO.getTipo(), valorNormalizado);

        if (chavePixRepository.existsByValor(valorNormalizado)) {
            throw new IllegalArgumentException(DUPLICATE_KEY_MESSAGE);
        }

        ChavePix chavePix = new ChavePix();
        chavePix.setTipo(requestDTO.getTipo());
        chavePix.setValor(valorNormalizado);

        return salvarComTratamentoDeConflito(chavePix);
    }

    public ChavePixResponseDTO atualizar(Long id, ChavePixRequestDTO requestDTO) {
        ChavePix existente = chavePixRepository.findById(id)
                .orElseThrow(() -> new ChavePixNotFoundException(id));

        String valorNormalizado = normalizarValor(requestDTO.getTipo(), requestDTO.getValor());
        validarFormatoPorTipo(requestDTO.getTipo(), valorNormalizado);

        chavePixRepository.findByValor(valorNormalizado)
                .filter(chavePix -> !chavePix.getId().equals(id))
                .ifPresent(chavePix -> {
                    throw new IllegalArgumentException(DUPLICATE_KEY_MESSAGE);
                });

        existente.setTipo(requestDTO.getTipo());
        existente.setValor(valorNormalizado);

        return salvarComTratamentoDeConflito(existente);
    }

    public void remover(Long id) {
        ChavePix chavePix = chavePixRepository.findById(id)
                .orElseThrow(() -> new ChavePixNotFoundException(id));
        chavePixRepository.delete(chavePix);
    }

    private ChavePixResponseDTO toResponseDTO(ChavePix chavePix) {
        ChavePixResponseDTO dto = new ChavePixResponseDTO();
        dto.setId(chavePix.getId());
        dto.setTipo(chavePix.getTipo());
        dto.setValor(chavePix.getValor());
        return dto;
    }

    private ChavePixResponseDTO salvarComTratamentoDeConflito(ChavePix chavePix) {
        try {
            ChavePix salvo = chavePixRepository.save(chavePix);
            return toResponseDTO(salvo);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalArgumentException(DUPLICATE_KEY_MESSAGE);
        }
    }

    private String normalizarValor(TipoChavePix tipo, String valor) {
        String valorSemEspacos = valor.trim();
        if (tipo == TipoChavePix.EMAIL) {
            return valorSemEspacos.toLowerCase(Locale.ROOT);
        }
        return valorSemEspacos;
    }

    private void validarFormatoPorTipo(TipoChavePix tipo, String valor) {
        switch (tipo) {
            case CPF -> {
                if (!valor.matches("\\d{11}")) {
                    throw new IllegalArgumentException("CPF deve ter 11 numeros");
                }
            }
            case EMAIL -> {
                if (!valor.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    throw new IllegalArgumentException("Email invalido");
                }
            }
            case CELULAR -> {
                if (!valor.matches("^\\+?\\d{10,13}$")) {
                    throw new IllegalArgumentException("Celular invalido. Exemplo: 11999998888");
                }
            }
        }
    }
}
