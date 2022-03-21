package com.vendas.gestaovendas.DTO.Categoria;

import com.vendas.gestaovendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel("Categoria Requisição DTO")
public class CategoriaRequestDTO {

    @ApiModelProperty(value = "Ex. Material de Contrução")
    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;

    public Categoria converteParaEntidade(){

        return new Categoria(nome);
    }

    public Categoria converteParaEntidade(Long codigo){

        return new Categoria(codigo,nome);
    }
}
