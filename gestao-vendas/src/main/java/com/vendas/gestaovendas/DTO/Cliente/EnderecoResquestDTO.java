package com.vendas.gestaovendas.DTO.Cliente;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ApiModel(value = "Endereco Requisição DTO")
public class EnderecoResquestDTO {

    @ApiModelProperty(value = "Lougradoro")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 30, message = "Logradouuro")
    private String logradouro;

    @ApiModelProperty(value = "Numero")
    @NotNull(message = "Numero")
    private Integer numero;

    @ApiModelProperty(value = "Complemento")
    @Length(min = 3, max = 30, message = "Complemento")
    private String complemento;

    @ApiModelProperty(value = "Bairro")
    @NotBlank(message = "Bairro")
    @Length(min = 3, max = 30, message = "Bairro")
    private String bairro;

    @ApiModelProperty(value = "Cep")
    @NotBlank(message = "CEP")
    @Pattern(regexp = "[\\d]{5}-[\\d]{3}", message = "CEP")
    private String cep;

    @ApiModelProperty(value = "Cidade")
    @NotBlank(message = "CEP")
    @Length(min = 3, max = 30, message = "Cidade")
    private String cidade;

    @ApiModelProperty(value = "Estado")
    @NotBlank(message = "CEP")
    @Length(min = 3, max = 30, message = "Estado")
    private String estado;
}
