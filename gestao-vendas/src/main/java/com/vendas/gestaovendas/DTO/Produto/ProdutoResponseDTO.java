package com.vendas.gestaovendas.DTO.Produto;

import com.vendas.gestaovendas.DTO.Categoria.CategoriaResonseDTO;
import com.vendas.gestaovendas.entidades.Produtos;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponseDTO {


    @ApiModelProperty(value = "Ex. 10")
    private Long codigo;

    @ApiModelProperty(value = "Ex. Smartphone Sony")
    private String descricao;

    @ApiModelProperty(value = "Ex. 5")
    private int quantidade;

    @ApiModelProperty(value = "Ex. 650.00")
    private BigDecimal precoCusto;

    @ApiModelProperty(value = "Ex. 800.00")
    private BigDecimal precoVenda;

    @ApiModelProperty(value = "Ex. Cor: Azul")
    private String observacao;

    @ApiModelProperty(value = "Categoria")
    private CategoriaResonseDTO categoria;

    public ProdutoResponseDTO(Long codigo, String descricao, int quantidade, BigDecimal precoCusto, BigDecimal precoVenda, String observacao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.observacao = observacao;
        this.categoria = categoria;
    }

    public static ProdutoResponseDTO converterParaProdutoDTO(Produtos produtos){
        return new ProdutoResponseDTO(produtos.getCodigo(), produtos.getDescricao(), produtos.getQuantidade(),
                produtos.getPrecoCusto(), produtos.getPrecoVenda(), produtos.getObservacao());
    }


}
