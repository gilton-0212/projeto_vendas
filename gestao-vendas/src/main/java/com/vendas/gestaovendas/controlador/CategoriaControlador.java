package com.vendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vendas.gestaovendas.DTO.Categoria.CategoriaRequestDTO;
import com.vendas.gestaovendas.DTO.Categoria.CategoriaResponseDTO;
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
	public List<CategoriaResponseDTO> listarTodas(){
		return categoriaSevico.listarTodas().stream().map(categoria -> CategoriaResponseDTO.converterParaCategoria(categoria)).
				collect(Collectors.toList());
	}

	@ApiOperation(value = "Listar por Codigo", nickname = "ListarCategoriaPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaSevico.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(CategoriaResponseDTO.converterParaCategoria(categoria.get())):
				ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar", nickname = "SalvarCategoria")
	@PostMapping
	public ResponseEntity<CategoriaResponseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriadto){
		Categoria categoriaSalva =  categoriaSevico.salvar(categoriadto.converterParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponseDTO.converterParaCategoria(categoriaSalva));
	}

	@ApiOperation(value = "Atualizar", nickname = "AtualizarCategoria")
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriadto){
		return ResponseEntity.ok(categoriaSevico.atualizar(codigo, categoriadto.converterParaEntidade(codigo)));
	}

	@ApiOperation(value = "Deletar", nickname = "DeeletarCategoria")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long codigo){
		categoriaSevico.deletar(codigo);
	}
}