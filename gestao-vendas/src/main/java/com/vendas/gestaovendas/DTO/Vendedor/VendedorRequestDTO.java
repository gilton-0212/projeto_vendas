package com.vendas.gestaovendas.DTO.Vendedor;

import com.vendas.gestaovendas.entidades.Categoria;
import com.vendas.gestaovendas.entidades.Vendedor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@ApiModel("Vendedor Request DTO")
public class VendedorRequestDTO {

    @ApiModelProperty(value = "Nome")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "nome")
    private String nome;

    public Vendedor converterParaEntidade(){

        return new Vendedor(nome);
    }

    public Vendedor converterParaEntidade(Long codigo){

        return new Vendedor(codigo,nome);
    }

    public String getNome() {

        return nome;
    }

    public void setNome(String nome) {

        this.nome = nome;
    }
}
