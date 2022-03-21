package com.vendas.gestaovendas.controlador;

import com.vendas.gestaovendas.DTO.Produto.ProdutoRequestDTO;
import com.vendas.gestaovendas.DTO.Produto.ProdutoResponseDTO;
import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Produtos;
import com.vendas.gestaovendas.excecao.RegraDeNegocioException;
import com.vendas.gestaovendas.servico.CategoriaServico;
import com.vendas.gestaovendas.servico.ProdutoServico;
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

@Api(tags = "Produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoControladora {

    @Autowired
    private ProdutoServico produtoServico;

    @Autowired
    private CategoriaServico categoriaServico;

    /*
    @ApiOperation(value = "Listar Todos", nickname = "ListarTodosProdutos")
    @GetMapping
    public List<Produtos> listarTodos(@PathVariable Long codigoCategoria){
        return produtoServico.listarTodos(codigoCategoria);
    }

    @ApiOperation(value = "Listar por codigo", nickname = "BuscarProdutoPorCodigo")
    @GetMapping("categoria/{codigo}")
    public ResponseEntity<Optional<Produtos>> buscarPorCodigo(@PathVariable Long codigoCatgoria, @PathVariable Long codigo){
        Optional<Produtos> produtos = produtoServico.buscarPorCodigo(codigo,codigoCatgoria);
        return produtos.isPresent() ? ResponseEntity.ok(produtos) : ResponseEntity.notFound().build();
    }

     */

    @ApiOperation(value = "Listar Todos", nickname = "ListarTodosProdutos")
    @GetMapping
    public List<ProdutoResponseDTO> listarTodos(){
        return produtoServico.ListarTodos().stream().map(produtos -> ProdutoResponseDTO.converterParaProdutoDTO(produtos)).
                collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por codigo", nickname = "BuscarProdutoPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> listarPorCodigo(@PathVariable Long codigo){
        Optional<Produtos> produtos = produtoServico.buscarPorCodigo(codigo);
        return produtos.isPresent() ? ResponseEntity.ok(ProdutoResponseDTO.converterParaProdutoDTO(produtos.get())) :
                ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Listar Produto por Categoria", nickname = "ListarProdutoPorCategoria")
    @GetMapping("categoria/{codigo}")
    public List<ProdutoResponseDTO> listarProdutoPorCategoria(@PathVariable Long codigo){
        return produtoServico.buscarProdutosPorCategoria(codigo).stream().map(produtos -> ProdutoResponseDTO.converterParaProdutoDTO(produtos)).
                collect(Collectors.toList());
    }

    @ApiOperation(value = "Salvar", nickname = "SalvarProduto")
    @PostMapping("")
    public ResponseEntity<Produtos> salvar(@Valid @RequestBody Produtos produtos){
      Produtos produtoSalvo = produtoServico.salvar(produtos);
      return (ResponseEntity<Produtos>) ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @ApiOperation(value = "Atualizar", nickname = "atualizarProduto")
    @PutMapping("/{codigoProduto}")
    public ResponseEntity<Produtos> atualizar( @PathVariable Long codigoProduto
            ,@Valid @RequestBody Produtos produtos){
        Produtos produtoAtualizado = produtoServico.atualizar( codigoProduto, produtos);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @ApiOperation(value = "Deletar", nickname = "DeletarProduto")
    @DeleteMapping("{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigo){
        produtoServico.Deletar(codigo);
    }

}