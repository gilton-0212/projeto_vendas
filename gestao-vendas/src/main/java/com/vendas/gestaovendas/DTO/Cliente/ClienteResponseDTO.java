package com.vendas.gestaovendas.DTO.Cliente;

import com.vendas.gestaovendas.entidades.Cliente;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Cliente Retorno DTO")
public class ClienteResponseDTO {


    @ApiModelProperty(value = "Código. Ex. 10")
    private Long codigo;

    @ApiModelProperty(value = "Nome. Ex. Pedro")
    private String nome;

    @ApiModelProperty(value = "Telefone. Ex. 79999999999")
    private String telefone;

    @ApiModelProperty(value = "Ativo. Ex. Ativo")
    private Boolean ativo;

    private EnderecoResponseDTO enderecoDTO;

    public ClienteResponseDTO(Long codigo, String nome, String telefone, Boolean ativo, EnderecoResponseDTO enderecoDTO) {
        this.codigo = codigo;
        this.nome = nome;
        this.telefone = telefone;
        this.ativo = ativo;
        this.enderecoDTO = enderecoDTO;
    }

    public static ClienteResponseDTO converterParaClienteDTO(Cliente cliente){
        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO (cliente.getEndereco().getLogradouro(), cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(), cliente.getEndereco().getBairro(),cliente.getEndereco().getCep(),
                cliente.getEndereco().getCidade(), cliente.getEndereco().getEstado());
        return new ClienteResponseDTO(cliente.getCodigo(), cliente.getNome(), cliente.getTelefone(), cliente.getAtivo(), enderecoResponseDTO);
    }
}
