package com.vendas.gestaovendas.repositorio;

import com.vendas.gestaovendas.entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepositorio extends JpaRepository<Produtos, Long>{

    /*
    List<Produtos> findByCategoriaCodigo(Long codigoCategoria);
    @Query("Select prod"
            + "from Produtos prod"
            + "where prod.codigo = :codigo"
            + "and prod.categoria.codigo = :codigoCategoria")
    Optional<Produtos> buscarPorCodigo(Long codigo, Long codigoCategoria);

     */

    List<Produtos> findByCategoriaCodigo(Long codigo);

    Optional<Produtos> findByCategoriaCodigoAndDescricao(Long codigoCategoria, String descricao);

}