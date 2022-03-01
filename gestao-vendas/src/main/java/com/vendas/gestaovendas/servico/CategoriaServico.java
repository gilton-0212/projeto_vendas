package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendas.gestaovendas.repositorio.CategoriaRepositorio;
import com.vendas.gestaovendas.entidades.Categoria;

@Service
public class CategoriaServico {

	@Autowired
	private CategoriaRepositorio categoriaRepositorio;

	public List<Categoria> listarTodas() {
		return categoriaRepositorio.findAll();

	}

	public Optional<Categoria> buscarPorId(Long codigo) {
		return categoriaRepositorio.findById(codigo);
	}
}
