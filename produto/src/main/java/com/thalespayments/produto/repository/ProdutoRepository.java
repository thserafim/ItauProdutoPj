package com.thalespayments.produto.repository;



import com.thalespayments.produto.model.ProdutoPj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoPj, Long>{
}
