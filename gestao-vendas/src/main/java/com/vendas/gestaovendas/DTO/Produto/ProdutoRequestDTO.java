package com.vendas.gestaovendas.DTO.Produto;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Produtos;
import com.vendas.gestaovendas.mappers.CategoriaMapperImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Produtos;

@ApiModel("Produto Requisição DTO")
public class ProdutoRequestDTO {

       @ApiModelProperty(value = "Ex. Smartphone Sony")
       @NotBlank(message = "Descrição")
       @Length(min = 3, max = 100, message = "Descrição")
       private String descricao;

       @ApiModelProperty(value = "Ex. 5")
       @NotNull(message = "Quantidade")
       private int quantidade;

       @ApiModelProperty(value = "Ex. 650.00")
       @NotNull(message = "Preço custo")
       private BigDecimal precoCusto;

       @ApiModelProperty(value = "Ex. 800.00")
       @NotNull(message = "Preço venda")
       private BigDecimal precoVenda;

       @ApiModelProperty(value = "Ex. Cor: Azul")
       @Length(max = 500, message = "Observação")
       private String observacao;

       @NotNull(message = "Codigo Categoria")
       @ManyToOne
       @JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
       private Categoria categoria;

       public Produtos converterParaEntidade(){
           return new Produtos(descricao,quantidade,precoCusto,precoVenda,observacao,categoria);
       }

       public Produtos converterParaEntidade(Long codigoProduto){
              return new Produtos(descricao,quantidade,precoCusto,precoVenda,observacao,categoria);
       }
}

