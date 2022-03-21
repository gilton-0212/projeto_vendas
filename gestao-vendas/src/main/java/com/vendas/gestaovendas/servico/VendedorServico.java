package com.vendas.gestaovendas.servico;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Vendedor;
import com.vendas.gestaovendas.excecao.RegraDeNegocioException;
import com.vendas.gestaovendas.repositorio.VendedorRepositorio;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendedorServico {

    @Autowired
    private VendedorRepositorio vendedorRepositorio;

    public List<Vendedor> listarTodos(){
        return vendedorRepositorio.findAll();
    }

    public Optional<Vendedor> buscarPorCodigo(Long codigo){
        return vendedorRepositorio.findById(codigo);
    }

    public Vendedor salvar(Vendedor vendedor){
        validarVendedorDuplicada(vendedor);
        return vendedorRepositorio.save(vendedor);
    }

    public Vendedor atualizar(Long codigo, Vendedor vendedor){
        Vendedor vendedorSalvar = validarVendedorexiste(codigo);
        validarVendedorDuplicada(vendedor);
        BeanUtils.copyProperties(vendedor, vendedorSalvar, "codigo");
        return vendedorRepositorio.save(vendedorSalvar);
    }

    public void deletar (Long codigo){
        vendedorRepositorio.deleteById(codigo);
    }

    private Vendedor validarVendedorexiste(Long codigo) {
        Optional<Vendedor> vendedor = buscarPorCodigo(codigo);
        if (vendedor.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return vendedor.get();
    }

    private void validarVendedorDuplicada(Vendedor vendedor){
        Vendedor vendedorEncontrado = vendedorRepositorio.findByNome(vendedor.getNome());
        if(vendedorEncontrado != null && vendedorEncontrado.getCodigo() != vendedor.getCodigo()){
            throw new RegraDeNegocioException(String.format("A categoria %s já esta cadastrada", vendedor.getNome().toUpperCase()));

        }
    }

}
