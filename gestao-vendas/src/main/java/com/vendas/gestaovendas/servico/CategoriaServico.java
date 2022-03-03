package com.vendas.gestaovendas.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

	public Optional<Categoria> buscarPorCodigo(Long codigo) {
		return categoriaRepositorio.findById(codigo);
	}

	public Categoria salvar(Categoria categoria){
		return categoriaRepositorio.save(categoria);
	}

	public Categoria atualizar(Long codigo, Categoria categoria){
		Categoria categoriaSalvar = validarcategoriaexiste(codigo);
		BeanUtils.copyProperties(categoria, categoriaSalvar, "codio");
		return categoriaRepositorio.save(categoriaSalvar);
	}

	private Categoria validarcategoriaexiste(Long codigo) {
		Optional<Categoria> categoria = buscarPorCodigo(codigo);
		if (categoria.isEmpty()){
			throw new EmptyResultDataAccessException(1);
		}
		return categoria.get();

	}
}
