package com.vendas.gestaovendas.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "vendedor")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "nome")
    private String nome;

    public Vendedor(){

    }

    public Vendedor(String nome) {
        this.nome = nome;
    }

    public Vendedor(Long codigo) {
        this.codigo = codigo;
    }

    public Vendedor(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
}
