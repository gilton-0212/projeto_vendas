package com.vendas.gestaovendas.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class Endereco {

    @Column(name = "logradouro" )
    private String logradouro;

    @Column(name = "numero" )
    private Integer numero;

    @Column(name =  "complemento" )
    private String complemento;

    @Column(name = "bairro" )
    private String bairro;

    @Column(name = "cep" )
    private String cep;

    @Column(name = "cidade" )
    private String cidade;

    @Column(name = "estado")
    private String estado;

    public Endereco(){

    }

    public Endereco(String logradouro, Integer numero, String complemento, String bairro, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco endereco = (Endereco) o;
        return logradouro.equals(endereco.logradouro) && numero.equals(endereco.numero)
                && complemento.equals(endereco.complemento) && bairro.equals(endereco.bairro)
                && cep.equals(endereco.cep) && cidade.equals(endereco.cidade)
                && estado.equals(endereco.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logradouro, numero, complemento, bairro, cep, cidade, estado);
    }
}
