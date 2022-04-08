package com.vendas.gestaovendas.controlador;


import com.vendas.gestaovendas.DTO.Produto.ProdutoResponseDTO;
import com.vendas.gestaovendas.DTO.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.DTO.Venda.VendaRequestDTO;
import com.vendas.gestaovendas.entidades.ItemVenda;
import com.vendas.gestaovendas.entidades.Venda;
import com.vendas.gestaovendas.servico.VendaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Vendas")
@RestController
@RequestMapping("/venda")
public class VendaControlador {

    @Autowired
    private VendaServico vendaServico;


    @ApiOperation(value = "Listar Todos Itens ", nickname = "ListarTodasItensDaVenda")
    @GetMapping
    public List<ItemVenda> listarTodosItens(){
        return vendaServico.ListarTodosItens();
        //.stream().map(produtos -> ProdutoResponseDTO.converterParaProdutoDTO(produtos)).
        //collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar vendas por cliente", nickname = "listarVendaPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente(@PathVariable Long codigoCliente){
        return ResponseEntity.ok(vendaServico.listaVendaPorCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar vendas por código", nickname = "listarVendaPorCodigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo(@PathVariable Long codigoVenda){
        return ResponseEntity.ok(vendaServico.listarVendaPorCodigo(codigoVenda));
    }

    @ApiOperation(value = "Registrar Venda", nickname = "salvar")
    @PostMapping
    public ResponseEntity<ClienteVendaResponseDTO> salvar(@Valid @RequestBody VendaRequestDTO vendaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vendaServico.salvar(vendaDto));
    }

    @ApiOperation(value = "Atualizar Venda", nickname = "atualizar")
    @PutMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> atualizar(@PathVariable Long codigoVenda, @Valid @RequestBody VendaRequestDTO vendaDto){
        return ResponseEntity.ok(vendaServico.atualizar(codigoVenda, vendaDto));
    }

    @ApiOperation(value = "Deletar vendas por codigo", nickname = "DeletarVendasPorCodigo")
    @DeleteMapping("/{codigoVenda}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long codigoVenda) {
        vendaServico.deletar(codigoVenda);
    }
}
