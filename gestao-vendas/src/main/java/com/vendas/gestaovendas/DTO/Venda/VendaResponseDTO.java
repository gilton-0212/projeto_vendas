package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda retorno DTO")
public class VendaResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Long codigo;

    @ApiModelProperty(value = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Ativo")
    private Boolean ativo;

    @ApiModelProperty(value = "Itens da venda")
    private List<ItemVendaResponseDTO> itemVendaDTOs;

    @ApiModelProperty(value = "Nome do Vendedor")
    private String nomeVendedor;

    @ApiModelProperty(value = "Nome do Cliente")
    private String nomeCliente;

    public VendaResponseDTO(Long codigo, LocalDate data, Boolean ativo,
                            List<ItemVendaResponseDTO> itemVendaDTOs, String nomeVendedor, String nomeCliente) {
        this.codigo = codigo;
        this.data = data;
        this.ativo = ativo;
        this.itemVendaDTOs = itemVendaDTOs;
        this.nomeVendedor = nomeVendedor;
        this.nomeCliente = nomeCliente;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<ItemVendaResponseDTO> getItemVendaDTOs() {
        return itemVendaDTOs;
    }

    public void setItemVendaDTOs(List<ItemVendaResponseDTO> itemVendaDTOs) {
        this.itemVendaDTOs = itemVendaDTOs;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
