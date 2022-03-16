package com.vendas.gestaovendas.controlador;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vendas.gestaovendas.DTO.Categoria.CategoriaRequestDTO;
import com.vendas.gestaovendas.DTO.Categoria.CategoriaResonseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
	@GetMapping
	public List<CategoriaResonseDTO> listarTodas(){
		return categoriaSevico.listarTodas().stream().map(categoria -> CategoriaResonseDTO.converterParaCategoriaDTO(categoria)).
				collect(Collectors.toList());
		/*listarTodas().stream().map(categoria -> CategoriaResonseDTO.converterParaCategoriaDTO(categoria))
		.collect(Collectors.toList());
		*/
	}

	@ApiOperation(value = "Listar por Codigo", nickname = "ListarCategoriaPorCodigo")
	@GetMapping("/{codigo}")
	public ResponseEntity<CategoriaResonseDTO> buscarPorId(@PathVariable Long codigo){
		Optional<Categoria> categoria = categoriaSevico.buscarPorCodigo(codigo);
		return categoria.isPresent() ? ResponseEntity.ok(CategoriaResonseDTO.converterParaCategoriaDTO(categoria.get())) : ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salvar", nickname = "SalvarCategoria")
	@PostMapping
	public ResponseEntity<CategoriaResonseDTO> salvar(@Valid @RequestBody CategoriaRequestDTO categoriadto){
		Categoria categoriaSalva =  categoriaSevico.salvar(categoriadto.converteParaEntidade());
		return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResonseDTO.converterParaCategoriaDTO(categoriaSalva));
	}

	@ApiOperation(value = "Atualizar", nickname = "AtualizarCategoria")
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody CategoriaRequestDTO categoriadto){
		return ResponseEntity.ok(categoriaSevico.atualizar(codigo, categoriadto.converteParaEntidade(codigo)));
	}

	@ApiOperation(value = "Deletar", nickname = "DeeletarCategoria")
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable long codigo){
		categoriaSevico.deletar(codigo);
	}
}