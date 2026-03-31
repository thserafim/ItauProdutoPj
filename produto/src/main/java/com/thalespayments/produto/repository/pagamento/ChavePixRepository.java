package com.thalespayments.produto.repository.pagamento;

import com.thalespayments.produto.model.pagamento.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChavePixRepository extends JpaRepository<ChavePix, Long> {
    boolean existsByValor(String valor);
    Optional<ChavePix> findByValor(String valor);
}
