package com.vendas.gestaovendas.DTO.Venda;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.Vendedor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ApiModel("Venda Retorno DTO")
public class VendaResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Long codigo;

    @ApiModelProperty(value = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Ativo")
    private Boolean ativo;

    @ApiModelProperty(value = "Itens da venda")
    private List<ItemVendaResponseDTO> ItemVendaResponseDTO;

    @ApiModelProperty(value = "Nome do Vendedor")
    private String nomeVendedor;

    @ApiModelProperty(value = "Nome do Cliente")
    private String nomeCliente;

    public VendaResponseDTO(Long codigo, LocalDate data, Boolean ativo,
                            List<ItemVendaResponseDTO> itemVendaResponseDTO, Cliente cliente, Vendedor vendedor) {
        this.codigo = codigo;
        this.data = data;
        this.ativo = ativo;
        ItemVendaResponseDTO = itemVendaResponseDTO;
        this.nomeVendedor = nomeVendedor;
        this.nomeCliente = nomeCliente;
    }
}
