package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@ApiModel("Itens Venda Requisição")
@Data
public class ItemVendaRequestDTO {

    @ApiModelProperty(value = "Quantidade")
    @NotNull(message = "Quantidade")
    @Min(value = 1, message = "Quantidade")
    private Integer quantidade;

    @ApiModelProperty(value = "Preco Vendido")
    @NotNull(message = "Preço Vendido")
    private BigDecimal precoVendido;

    @ApiModelProperty(value = "Codigo Produto")
    @NotNull(message = "Codigo Produto")
    private Long codigoProduto;

}
