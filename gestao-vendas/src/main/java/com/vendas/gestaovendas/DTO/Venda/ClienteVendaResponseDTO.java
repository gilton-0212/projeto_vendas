package com.vendas.gestaovendas.DTO.Venda;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Cliente da Venda Retorno DTO")
public class ClienteVendaResponseDTO {

    @ApiModelProperty(value = "nome Cliente")
    private String nome;

    @ApiModelProperty(value = "Venda Cliente")
    private List<VendaResponseDTO> vendaResponseDTO;

    public ClienteVendaResponseDTO(String nome, List<VendaResponseDTO> vendaResponseDTO) {
        this.nome = nome;
        this.vendaResponseDTO = vendaResponseDTO;
    }
}
