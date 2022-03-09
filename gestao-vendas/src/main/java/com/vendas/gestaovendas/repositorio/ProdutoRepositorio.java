package com.vendas.gestaovendas.repositorio;

import com.vendas.gestaovendas.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorio extends JpaRepository<Produtos, Long>{

    List<Produtos> findByCategoriaCodigo(Long codigo);

    Optional<Produtos> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);
}