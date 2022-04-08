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
    private Integer quantidade;

    @Column(name = "preco_vendido")
    private BigDecimal precoVendido;

    @ManyToOne
    @JoinColumn(name = "codigo_produto", referencedColumnName = "codigo")
    private Produtos produtos;

    @ManyToOne
    @JoinColumn(name = "codigo_venda", referencedColumnName = "codigo")
    private Venda venda;


    public ItemVenda() {

    }
    public ItemVenda(Produtos produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemVenda)) return false;
        ItemVenda itemVenda = (ItemVenda) o;
        return codigo.equals(itemVenda.codigo) && quantidade.equals(itemVenda.quantidade) && precoVendido.equals(itemVenda.precoVendido) && produtos.equals(itemVenda.produtos) && venda.equals(itemVenda.venda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, quantidade, precoVendido, produtos, venda);
    }

}
