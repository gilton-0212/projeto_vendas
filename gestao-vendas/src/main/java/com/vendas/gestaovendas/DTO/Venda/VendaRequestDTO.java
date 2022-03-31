package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.List;

@ApiModel("Venda Requisição DTO")
public class VendaRequestDTO {

    @ApiModelProperty(name = "Date")
    private LocalDate data;

    @ApiModelProperty(name = "Ativo")
    private Boolean ativo;

    @ApiModelProperty(name = "Itens da venda")
    List<ItemVendaRequestDTO> itensVendaDTO;

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

    public List<ItemVendaRequestDTO> getItensVendaDTO() {
        return itensVendaDTO;
    }

    public void setItensVendaDTO(List<ItemVendaRequestDTO> itensVendaDTO) {
        this.itensVendaDTO = itensVendaDTO;
    }
}
