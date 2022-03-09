package com.vendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendas.gestaovendas.servico.CategoriaServico;
import com.vendas.gestaovendas.entidades.Categoria;

import javax.validation.Valid;

@Api(tags = "Categoria")
@RestController
@RequestMapping("/categoria")

public class CategoriaControlador {

	@Autowired
	private CategoriaServico categoriaSevico;

	@ApiOperation(value = "listar" , nickname = "ListarTodasCategorias")
	@GetMapping("")
	public List<Categoria> listarTodas(){
		return categoriaSevico.listarTodas();
	}

	@ApiOperation(value = "Listar por Codigo", nickname = "ListarCategoriaPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<Optional<Categoria>> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaSevico.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar", nickname = "SalvarCategoria")
	@PostMapping
	public ResponseEntity<Categoria> salvar(@Valid @RequestBody Categoria categoria){
		Categoria categoriaSalva =  categoriaSevico.salvar(categoria);
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	@ApiOperation(value = "Atualizar", nickname = "AtualizarCategoria")
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria){
		return ResponseEntity.ok(categoriaSevico.atualizar(codigo, categoria));
	}

	@ApiOperation(value = "Deletar", nickname = "DeeletarCategoria")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long codigo){
		categoriaSevico.deletar(codigo);
	}
}