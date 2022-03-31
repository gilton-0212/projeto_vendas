package com.vendas.gestaovendas.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "venda")
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo")
    private Long codigo;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "ativo")
    private Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "codigo_cliente", referencedColumnName = "codigo")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "codigo_vendedor", referencedColumnName = "codigo")
    private Vendedor vendedor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Venda)) return false;
        Venda venda = (Venda) o;
        return codigo.equals(venda.codigo) && data.equals(venda.data) && cliente.equals(venda.cliente) && vendedor.equals(venda.vendedor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, data, cliente, vendedor);
    }

}