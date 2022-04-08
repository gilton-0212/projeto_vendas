package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@ApiModel("Itens Venda Requisição")
@Data
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preco Vendido")
    private BigDecimal precoVendido;

    @ApiModelProperty(value = "Codigo Produto")
    private Long codigoProduto;

}
