package com.vendas.gestaovendas.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Setter
@Getter
@Table(name = "produto")

public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;
	
	@Column(name = "descricao")
	private String descricao;

	@Column(name = "quantidade")
	private int quantidade;

	@Column(name = "preco_custo")
	private BigDecimal precoCusto;

	@Column(name = "preco_venda")
	private BigDecimal precoVenda;

	@Column(name = "observacao")
	private String observacao;

	@ManyToOne
	@JoinColumn(name = "codigo_categoria", referencedColumnName = "codigo")
	private Categoria categoria;

	public Produtos(){

	}

	public Produtos(Long codigo) {
		this.codigo = codigo;
	}

	public Produtos(String descricao, Integer quantidade, BigDecimal precoCusto, BigDecimal precoVenda,
					String observacao, Categoria categoria) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.precoCusto = precoCusto;
		this.precoVenda = precoVenda;
		this.observacao = observacao;
		this.categoria = categoria;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
