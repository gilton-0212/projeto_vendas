package com.vendas.gestaovendas.servico;

import com.vendas.gestaovendas.excecao.RegraDeNegocioException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vendas.gestaovendas.entidades.Produtos;
import com.vendas.gestaovendas.repositorio.ProdutoRepositorio;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServico{

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private CategoriaServico categoriaServico;
    //codigo do curso
    /*
    public List<Produtos> listarTodos(Long codigoCategoria){
        return produtoRepositorio.findByCategoriaCodigo(codigoCategoria);
    }

    public Optional<Produtos> buscarPorCodigo(Long codigo, Long codigoCategoria){
        return produtoRepositorio.buscarPorCodigo(codigo,codigoCategoria);
    }

     */

   public List<Produtos> ListarTodos(){
        return produtoRepositorio.findAll();
    }

    public Optional<Produtos> buscarPorCodigo(Long codigo){
        return produtoRepositorio.findById(codigo);
    }

    public List<Produtos> buscarProdutosPorCategoria(Long codigo){
        return produtoRepositorio.findByCategoriaCodigo(codigo);
    }

    public Produtos salvar(Produtos produtos){
        validarCategoriaDoProdutoExiste(produtos.getCategoria().getCodigo());
        validarCategoriaDuplicado(produtos);
        return produtoRepositorio.save(produtos);
    }

    public Produtos atualizar(Long codigoProduto,Produtos produto) {
        Produtos produtoSalvar = validarProdutoExiste(codigoProduto);
        validarCategoriaDoProdutoExiste(produto.getCategoria().getCodigo());
        validarProdutoDuplicado(produto);
        BeanUtils.copyProperties(produto, produtoSalvar,"codigo");
        return produtoRepositorio.save(produtoSalvar);
    }


    private Produtos validarProdutoExiste(Long codigoProduto) {
        Optional<Produtos> produto = buscarPorCodigo(codigoProduto);
        if (produto.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }
        return produto.get();

    }

    private void validarProdutoDuplicado(Produtos produto) {
        Optional<Produtos> produtoPorDescricao = produtoRepositorio.findByCategoriaCodigoAndDescricao(produto.getCategoria().getCodigo(), produto.getDescricao());
        if(produtoPorDescricao.isPresent() && produtoPorDescricao.get().getCodigo() != produto.getCodigo()) {
            throw new RegraDeNegocioException(String.format("o produto %s ja está cadastrado", produto.getDescricao()));
        }
    }


    private void validarCategoriaDoProdutoExiste(Long codigoCategoria){
        if (codigoCategoria == null){
            throw new RegraDeNegocioException("A categoria não pode ser nula.");
        }
        if (categoriaServico.buscarPorCodigo(codigoCategoria).isEmpty()){
            throw new RegraDeNegocioException(String.format("A categoria %s já existe..", codigoCategoria));
        }
    }


    private void validarCategoriaDuplicado(Produtos produtos){
        if(produtoRepositorio.findByCategoriaCodigoAndDescricao(produtos.getCategoria().getCodigo(), produtos.getDescricao()).isPresent()){
            throw new RegraDeNegocioException(String.format("O produto %s já existe na categoria", produtos.getDescricao()));
        }
    }

    public void Deletar(Long codigo){
        produtoRepositorio.deleteById(codigo);
    }

}