package com.vendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendas.gestaovendas.servico.CategoriaServico;
import com.vendas.gestaovendas.entidades.Categoria;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")

public class CategoriaControlador {

	@Autowired
	private CategoriaServico categoriaSevico;
	
	@GetMapping("")
	public List<Categoria> listarTodas(){
		return categoriaSevico.listarTodas();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaSevico.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
		Categoria categoriaSalva =  categoriaSevico.salvar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
		return ResponseEntity.ok(categoriaSevico.atualizar(codigo, categoria));
	}
}
