package com.vendas.gestaovendas.DTO.Produto;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Produtos;
import com.vendas.gestaovendas.mappers.CategoriaMapperImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

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

       public Produtos converterParaEntidade(){
            return new Produtos(descricao,quantidade,precoCusto,precoVenda,observacao);
       }
       public Produtos converterParaEntidade(Long codigoProduto){
        return new Produtos(codigoProduto,descricao,quantidade,precoCusto,precoVenda,observacao);
       }

       public String getDescricao() {
         return descricao;
       }

       public void setDescricao(String descricao) {
        this.descricao = descricao;
       }

       public int getQuantidade() {
        return quantidade;
       }

       public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
       }

       public BigDecimal getPrecoCusto() {
        return precoCusto;
       }

       public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
       }

       public BigDecimal getPrecoVenda() {
        return precoVenda;
       }

       public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
       }

       public String getObservacao() {
        return observacao;
       }

       public void setObservacao(String observacao) {
        this.observacao = observacao;
       }

}

