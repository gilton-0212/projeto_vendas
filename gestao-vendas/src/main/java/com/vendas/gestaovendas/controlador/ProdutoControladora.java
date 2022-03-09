package com.vendas.gestaovendas.controlador;

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

import java.util.List;
import java.util.Optional;

@Api(tags = "Produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoControladora {

    @Autowired
    private ProdutoServico produtoServico;

    @Autowired
    private CategoriaServico categoriaServico;


    @ApiOperation(value = "Listar Todos", nickname = "ListarTodosProdutos")
    @GetMapping
    public List<Produtos> listarTodos(){
        return produtoServico.ListarTodos();
    }

    @ApiOperation(value = "Listar por codigo", nickname = "BuscarProdutoPorCodigo")
    @GetMapping("/{codigo}")
    public Optional<Produtos> listarPorCodigo(@PathVariable Long codigo){
        return produtoServico.buscarPorCodigo(codigo);
    }

    @ApiOperation(value = "Listar Produto por Categoria", nickname = "ListarProdutoPorCategoria")
    @GetMapping("categoria/{codigo}")
    public List<Produtos> listarProdutoPorCategoria(@PathVariable Long codigo){
        return produtoServico.buscarProdutosPorCategoria(codigo);
    }

    @ApiOperation(value = "Salvar", nickname = "SalvarProduto")
    @PostMapping("")
    public ResponseEntity<Produtos> salvar(@RequestBody Produtos produtos){
      Produtos produtoSalvo = produtoServico.salvar(produtos);
      return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    /*
    @ApiOperation(value = "Deletar")
    @DeleteMapping("{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long codigo){
        produtoServico.Deletar(codigo);
    }

     */
}