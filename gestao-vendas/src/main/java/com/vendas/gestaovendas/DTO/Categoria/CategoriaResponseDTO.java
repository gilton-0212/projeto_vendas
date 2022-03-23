package com.vendas.gestaovendas.DTO.Categoria;

import com.vendas.gestaovendas.entidades.Categoria;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@ApiModel("Categoria Response DTO")
public class CategoriaResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private long codigo;

    @ApiModelProperty(value = "Nome")
    private String nome;

    public CategoriaResponseDTO(long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static CategoriaResponseDTO converterParaCategoria(Categoria categoria){
        return new CategoriaResponseDTO(categoria.getCodigo(),categoria.getNome());
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
