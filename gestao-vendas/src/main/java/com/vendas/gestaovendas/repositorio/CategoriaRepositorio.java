package com.vendas.gestaovendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long>{

}
