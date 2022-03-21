package com.vendas.gestaovendas.DTO.Cliente;

import com.vendas.gestaovendas.entidades.Cliente;
import com.vendas.gestaovendas.entidades.Endereco;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@ApiModel(value = " Cliente Requisição DTO")
public class ClienteRequestDTO {

    @ApiModelProperty(value = "Nome. Ex. Pedro")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50)
    private String nome;

    @ApiModelProperty(value = "Telefone. Ex. 79999999999")
    @NotBlank(message = "Telefone")
    @Pattern(regexp = "\\([\\d]{2}\\)[\\d]{5}[- .][\\d]{4}", message = "Telefone")
    private String telefone;

    @ApiModelProperty(value = "Ativo. Ex. Ativo")
    @NotNull(message = "Ativo")
    private Boolean ativo;

    @ApiModelProperty(value = "Endereço")
    @NotNull(message = "Endereço")
    @Valid
    private EnderecoResquestDTO enderecoDTO;

    public Cliente converterParaEntidade(){
        Endereco endereco =  new Endereco(enderecoDTO.getLogradouro(), enderecoDTO.getNumero(), enderecoDTO.getComplemento(),
                enderecoDTO.getCep(), enderecoDTO.getBairro(), enderecoDTO.getCidade(), enderecoDTO.getEstado());
        return new Cliente(nome,telefone, ativo, endereco);
    }

    public Cliente converterParaEntidade(Long codigo){
        Endereco endereco =  new Endereco(enderecoDTO.getLogradouro(), enderecoDTO.getNumero(), enderecoDTO.getComplemento(),
                enderecoDTO.getCep(), enderecoDTO.getBairro(), enderecoDTO.getCidade(), enderecoDTO.getEstado());
        return new Cliente(codigo, nome,telefone, ativo, endereco);
    }


}
