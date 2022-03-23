package com.vendas.gestaovendas.DTO.Vendedor;

import com.vendas.gestaovendas.entidades.Vendedor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Vendedor Response DTO")
public class VendedorResponseDTO {

    @ApiModelProperty(value = "Codigo")
    private Long codigo;

    @ApiModelProperty(value = "Nome")
    private String nome;


    public VendedorResponseDTO(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public static VendedorResponseDTO converterParaVendedor(Vendedor vendedor){
        return new VendedorResponseDTO(vendedor.getCodigo(), vendedor.getNome());
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
