package com.vendas.gestaovendas.controlador;


import com.vendas.gestaovendas.DTO.Categoria.CategoriaResponseDTO;
import com.vendas.gestaovendas.DTO.Produto.ProdutoResponseDTO;
import com.vendas.gestaovendas.DTO.Vendedor.VendedorRequestDTO;
import com.vendas.gestaovendas.DTO.Vendedor.VendedorResponseDTO;
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
    public List<VendedorResponseDTO> listarTodas(){
        return vendedorServico.listarTodos().stream().
                map(vendedor -> VendedorResponseDTO.converterParaVendedor(vendedor)).collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por Codigo", nickname = "ListarVendedorPorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<VendedorResponseDTO> buscarPorId(@PathVariable Long codigo){
        Optional<Vendedor> vendedor = vendedorServico.buscarPorCodigo(codigo);
        return vendedor.isPresent() ? ResponseEntity.ok(VendedorResponseDTO.converterParaVendedor(vendedor.get())) :
                ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "SalvarVendedor")
    @PostMapping
    public ResponseEntity<VendedorResponseDTO> salvar(@Valid @RequestBody VendedorRequestDTO vendedorRequestDTO){
        Vendedor vendedorsalvo =  vendedorServico.salvar(vendedorRequestDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(VendedorResponseDTO.converterParaVendedor(vendedorsalvo));
    }

    @ApiOperation(value = "Atualizar", nickname = "AtualizarVendedor")
    @PutMapping("/{codigo}")
    public ResponseEntity<Vendedor> atualizar(@PathVariable Long codigo, @Valid @RequestBody VendedorRequestDTO vendedorRequestDTO){
        return ResponseEntity.ok(vendedorServico.atualizar(codigo,vendedorRequestDTO.converterParaEntidade(codigo)));
    }

    @ApiOperation(value = "Deletar", nickname = "DeeletarVendedor")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long codigo){
        vendedorServico.deletar(codigo);
    }
}
