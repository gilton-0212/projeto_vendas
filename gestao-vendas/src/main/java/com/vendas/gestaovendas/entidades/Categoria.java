package com.vendas.gestaovendas.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter
@Setter
@Table(name = "categoria")

public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;

	@Column(name = "nome")
	private String nome;

	public Categoria() {

	}

	public Categoria(Long codigo) {
		this.codigo = codigo;
	}

	public Categoria(String nome) {

		this.nome = nome;
	}

	public Categoria(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
}
