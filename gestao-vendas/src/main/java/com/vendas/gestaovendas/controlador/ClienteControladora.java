package com.vendas.gestaovendas.controlador;

import com.vendas.gestaovendas.DTO.Cliente.ClienteRequestDTO;
import com.vendas.gestaovendas.DTO.Cliente.ClienteResponseDTO;
import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.servico.ClienteServico;
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

@Api(tags = "Cliente")
@RestController
@RequestMapping("/cliente")
public class ClienteControladora {

    @Autowired
    private ClienteServico clienteServico;

    @ApiOperation(value = "listar" , nickname = "ListarTodosClientes")
    @GetMapping
    public List<ClienteResponseDTO> listarTodas(){

        return clienteServico.listarTodas().stream().map(cliente -> ClienteResponseDTO.converterParaClienteDTO(cliente)).
                collect(Collectors.toList());
    }

    @ApiOperation(value = "Listar por Codigo", nickname = "ListarClientePorCodigo")
    @GetMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long codigo){
        Optional<Cliente> cliente = clienteServico.buscarPorCodigo(codigo);
        return cliente.isPresent() ? ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(cliente.get()))
                : ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "Salvar", nickname = "SalvarCliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO clienteRequestDTO){
        Cliente clienteSalvo =  clienteServico.salvar(clienteRequestDTO.converterParaEntidade());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClienteResponseDTO.converterParaClienteDTO(clienteSalvo));
    }

    @ApiOperation(value = "Atualizar", nickname = "AtualizarCliente")
    @PutMapping("/{codigo}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody ClienteRequestDTO clienteDTO){
        Cliente clienteatualizado =  clienteServico.atualizar(codigo, clienteDTO.converterParaEntidade(codigo));
        return ResponseEntity.ok(ClienteResponseDTO.converterParaClienteDTO(clienteatualizado));
    }

    @ApiOperation(value = "Deletar", nickname = "DeletarCliente")
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long codigo){
        clienteServico.deletar(codigo);
    }
}
