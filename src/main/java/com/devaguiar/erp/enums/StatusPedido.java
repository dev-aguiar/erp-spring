package com.devaguiar.erp.enums;

public enum StatusPedido {
    EM_ANDAMENTO("Em andamento"),
    LIBERADO("Liberado"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private String valor;

    StatusPedido(String valor) {
        this.valor = valor;
    }
}
