package com.devaguiar.erp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusPedido {
    EM_ANDAMENTO("Em andamento"),
    LIBERADO("Liberado"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private String valor;

    StatusPedido(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

    @JsonCreator
    public static StatusPedido fromValor(String valor) {
        for (StatusPedido item : values()) {
            if (item.valor.equalsIgnoreCase(valor.trim())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Status de pedido inválido: " + valor);
    }
}
