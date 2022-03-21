package com.vendas.gestaovendas.controlador;


import com.vendas.gestaovendas.DTO.Categoria.CategoriaRequestDTO;
import com.vendas.gestaovendas.DTO.Categoria.CategoriaResonseDTO;
import com.vendas.gestaovendas.DTO.Produto.ProdutoResponseDTO;
import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Vendedor;
import com.vendas.gestaovendas.repositorio.VendedorRepositorio;
import com.vendas.gestaovendas.servico.VendedorServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(tags = "Vendedor")
@RestController
@RequestMapping("/vendedor")
public class VendedorControladora{

    @Autowired
    private VendedorServico vendedorServico;

    @ApiOperation(value = "listar" , nickname = "ListarTodosVendedores")
    @GetMapping("")
    public List<Vendedor> listarTodas(){
        return vendedorServico.listarTodos();
    }

    @ApiOperation(value = "Listar por Codigo", nickname = "ListarVendedorPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<Optional<Vendedor>> buscarPorId(@PathVariable Long codigo){
        Optional<Vendedor> categoria = vendedorServico.buscarPorCodigo(codigo);
        return categoria.isPresent() ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "SalvarVendedor")
    @PostMapping
    public ResponseEntity<Vendedor> salvar(@Valid @RequestBody Vendedor vendedor){
        Vendedor vendedorsalvo =  vendedorServico.salvar(vendedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(vendedorsalvo);
    }

    @ApiOperation(value = "Atualizar", nickname = "AtualizarVendedor")
    @PutMapping("/{codigo}")
    public ResponseEntity<Vendedor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Vendedor vendedor){
        return ResponseEntity.ok(vendedorServico.atualizar(codigo, vendedor));
    }

    @ApiOperation(value = "Deletar", nickname = "DeeletarVendedor")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long codigo){
        vendedorServico.deletar(codigo);
    }
}
