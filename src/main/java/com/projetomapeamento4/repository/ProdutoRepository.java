package com.projetomapeamento4.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetomapeamento4.entity.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Long>{

}
