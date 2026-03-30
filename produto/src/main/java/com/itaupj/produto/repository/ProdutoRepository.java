package com.itaupj.produto.repository;



import com.itaupj.produto.model.ProdutoPj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoPj, Long>{
}
