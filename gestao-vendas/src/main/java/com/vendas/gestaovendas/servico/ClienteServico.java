package com.vendas.gestaovendas.servico;

import ch.qos.logback.core.net.server.Client;
import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.excecao.RegraDeNegocioException;
import com.vendas.gestaovendas.repositorio.ClienteRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.lang.ref.Cleaner;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServico {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    public List<Cliente> listarTodas() {
        return clienteRepositorio.findAll();

    }
    public Optional<Cliente> buscarPorCodigo(Long codigo) {
        return clienteRepositorio.findById(codigo);
    }

    public Cliente salvar(Cliente cliente){
        validarClienteDuplicado(cliente);
        return clienteRepositorio.save(cliente);
    }

    public Cliente atualizar(Long codigo, Cliente cliente){
        Cliente clienteatualizar = validarClienteExiste(codigo);
        validarClienteDuplicado(cliente);
        BeanUtils.copyProperties(cliente, clienteatualizar, "codigo");
        return clienteRepositorio.save(clienteatualizar);
    }

    public void deletar(Long codigo){
        clienteRepositorio.deleteById(codigo);
    }

    private Cliente validarClienteExiste(Long codigo){
        Optional<Cliente> Cliente = buscarPorCodigo(codigo);
        if(Cliente.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return Cliente.get();
    }

    private void validarClienteDuplicado(Cliente cliente){
        Cliente clienteEncontrado = clienteRepositorio.findByNome(cliente.getNome());
        if(clienteEncontrado != null && clienteEncontrado.getCodigo() != cliente.getCodigo()){
            throw new RegraDeNegocioException(String.format("O Cliente %s já esta cadastrado", cliente.getNome().toUpperCase()));

        }
    }
}
