package com.vendas.gestaovendas.repositorio;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepositorio extends JpaRepository<Vendedor, Long>{
    Vendedor findByNome(String nome);

}
