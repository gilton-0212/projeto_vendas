package com.vendas.gestaovendas.DTO.Categoria;

import com.vendas.gestaovendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Categoria Retorno DTO")
public class CategoriaResonseDTO {

    @ApiModelProperty(value = "20")
    private long codigo;

    @ApiModelProperty(value = "Material Construção")
    private String nome;

    public CategoriaResonseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static CategoriaResonseDTO converterParaCategoriaDTO(Categoria categoria) {
        return new CategoriaResonseDTO(categoria.getCodigo(), categoria.getNome());
    }

}
