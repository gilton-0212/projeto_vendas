package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda Vendedor retorno DTO")
@Data
public class VendaVendedorResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Long codigo;

    @ApiModelProperty(value = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Itens da venda")
    private List<ItemVendaResponseDTO> ItemVendaResponseDTO;

    @ApiModelProperty(value = "Nome do Cliente")
    private String nomeCliente;

    public VendaVendedorResponseDTO(Long codigo, LocalDate data, List<ItemVendaResponseDTO> itemVendaResponseDTO, String nomeCliente) {
        this.codigo = codigo;
        this.data = data;
        ItemVendaResponseDTO = itemVendaResponseDTO;
        this.nomeCliente = nomeCliente;
    }
}
