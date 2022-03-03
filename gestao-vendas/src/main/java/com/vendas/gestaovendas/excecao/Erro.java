package com.vendas.gestaovendas.excecao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Erro {

    private String msgUsuario;

    private String msgDesenvolvedor;

    public Erro(String msgUsuario, String msgDesenvolvedor){

        this.msgDesenvolvedor = msgDesenvolvedor;
        this.msgUsuario = msgUsuario;

    }

}

