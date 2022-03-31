package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@ApiModel("Item Venda Retorno DTO")
public class ItemVendaResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Long codigo;

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preco Vendido")
    private BigDecimal precoVendido;

    @ApiModelProperty(value = "Codigo Produto")
    private Long codigoProduto;

    @ApiModelProperty(value = "Produto Descricao")
    private String produtoDescricao;

    public ItemVendaResponseDTO(Long codigo, Integer quantidade, BigDecimal precoVendido,
                                Long codigoProduto, String produtoDescricao) {
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.precoVendido = precoVendido;
        this.codigoProduto = codigoProduto;
        this.produtoDescricao = produtoDescricao;
    }
}
