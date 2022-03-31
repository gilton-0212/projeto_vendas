package com.vendas.gestaovendas.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "quantidade")
    private Integer quanidade;

    @Column(name = "preco_vendido")
    private BigDecimal precoVendido;

    @ManyToOne
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
    private Produtos produtos;

    @ManyToOne
    @JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
    private Venda venda;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVenda)) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return codigo.equals(itemVenda.codigo) && quanidade.equals(itemVenda.quanidade) && precoVendido.equals(itemVenda.precoVendido) && produtos.equals(itemVenda.produtos) && venda.equals(itemVenda.venda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, quanidade, precoVendido, produtos, venda);
    }

}
