package com.vendas.gestaovendas.repositorio;

import com.vendas.gestaovendas.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositorio extends JpaRepository<Produtos, Long>{

}