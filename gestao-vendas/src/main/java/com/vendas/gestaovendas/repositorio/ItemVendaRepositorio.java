package com.vendas.gestaovendas.repositorio;

import com.vendas.gestaovendas.entidades.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepositorio extends JpaRepository <ItemVenda, Long> {

    List<ItemVenda> findByVendaCodigo(Long codigoVenda);
}