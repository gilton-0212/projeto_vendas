package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda Requisição")
@Data
public class VendaRequestDTO {

    @ApiModelProperty(value = "Data")
    @NotNull(message = "Data")
    private LocalDate data;

    @ApiModelProperty(value = "Ativo")
    @NotNull(message = "Ativo")
    private Boolean ativo;

    @ApiModelProperty(value = "Itens")
    @NotNull(message = "Itens")
    @Valid
    private List<ItemVendaRequestDTO> ItensVendaDto;

    @ApiModelProperty(value = "Codigo Cliente")
    @NotNull(message = "Codigo Cliente")
    private Long codigoCliente;

    @ApiModelProperty(value = "Vendedor")
    @NotNull(message = "Codigo Vendedor")
    private Long codigoVendedor;

    public VendaRequestDTO(LocalDate data, Boolean ativo, List<ItemVendaRequestDTO> itensVendaDto, Long codigoCliente, Long codigoVendedor) {
        this.data = data;
        this.ativo = ativo;
        ItensVendaDto = itensVendaDto;
        this.codigoCliente = codigoCliente;
        this.codigoVendedor = codigoVendedor;
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

    public List<ItemVendaRequestDTO> getItensVendaDto() {
        return ItensVendaDto;
    }

    public void setItensVendaDto(List<ItemVendaRequestDTO> itensVendaDto) {
        ItensVendaDto = itensVendaDto;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Long getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(Long codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }
}
