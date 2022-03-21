package com.vendas.gestaovendas.DTO.Cliente;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
public class EnderecoResponseDTO {


    @ApiModelProperty(value = "Lougradoro")
    private String logradouro;

    @ApiModelProperty(value = "Numero")
    private Integer numero;

    @ApiModelProperty(value = "Complemento")
    private String complemento;

    @ApiModelProperty(value = "Bairro")
    private String bairro;

    @ApiModelProperty(value = "Cep")
    private String cep;

    @ApiModelProperty(value = "Cidade")
    private String cidade;

    @ApiModelProperty(value = "Estado")
    private String estado;

    public EnderecoResponseDTO(String logradouro, Integer numero, String complemento, String bairro,
                               String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }
}
