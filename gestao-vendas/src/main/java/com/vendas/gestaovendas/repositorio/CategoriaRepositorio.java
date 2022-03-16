package com.vendas.gestaovendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.entidades.Categoria;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{

    Categoria findByNome(String nome);

    /*@Query(value="select new com.vendas.gestaovendas.dto.NomeDTO(u.codigo, u.nome) from Categoria u where loweer(u.nome) like !1")
    List<NomeDTO> findByNome(String nome);


     */
}