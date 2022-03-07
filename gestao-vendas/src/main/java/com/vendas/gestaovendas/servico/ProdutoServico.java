package com.vendas.gestaovendas.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vendas.gestaovendas.entidades.Produtos;
import com.vendas.gestaovendas.repositorio.ProdutoRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico{

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public List<Produtos> ListarTodos(){
        return produtoRepositorio.findAll();
    }

    public Optional<Produtos> BuscarPorCodigo(Long codigo){
        return produtoRepositorio.findById(codigo);
    }
    
    public Produtos salvar(Produtos produtos){
        return produtoRepositorio.save(produtos);
    }

    public void Deletar(Long codigo){
        produtoRepositorio.deleteById(codigo);
    }
}