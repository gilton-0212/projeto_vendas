package com.vendas.gestaovendas.controlador;

import com.vendas.gestaovendas.DTO.Venda.ClienteVendaResponseDTO;
import com.vendas.gestaovendas.DTO.Venda.VendaResponseDTO;
import com.vendas.gestaovendas.servico.VendaServico;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "Vendas")
@RestController
@RequestMapping("/venda")
public class VendaControlador {


    @Autowired
    private VendaServico vendaServico;

    @ApiOperation(value = "Listar vendas", nickname = "ListarVendas")
    @GetMapping
    public List<VendaResponseDTO> listarTodos(){
        return vendaServico.listarTodos();
    }

    @ApiOperation(value = "Listar Vendas por Cliente", nickname = "ListarVendasPorCliente")
    @GetMapping("/cliente/{codigoCliente}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCliente (@PathVariable Long codigoCliente){
        return ResponseEntity.ok(vendaServico.listarPorCodigoCliente(codigoCliente));
    }

    @ApiOperation(value = "Listar Vendas por Codigo", nickname = "ListarVendasPorCodigo")
    @GetMapping("/{codigoVenda}")
    public ResponseEntity<ClienteVendaResponseDTO> listarVendaPorCodigo (@PathVariable Long codigoVenda){
        return ResponseEntity.ok(vendaServico.listarVendaPorCodigo(codigoVenda));
    }
}