package com.vendas.gestaovendas.repositorio;

import com.vendas.gestaovendas.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepositorio extends JpaRepository <Cliente, Long> {

    Cliente findByNome(String nome);
}
