package com.devaguiar.erp.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO("Cartão"),
    BOLETO("Boleto"),
    PIX("Pix");

    private String valor;

    FormaPagamento(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return this.valor;
    }

    @JsonCreator
    public static FormaPagamento fromValor(String valor) {
        for (FormaPagamento item : values()) {
            if (item.valor.equalsIgnoreCase(valor.trim())) {
                return item;
            }
        }
        throw new IllegalArgumentException("Forma de pagamento inválida: " + valor);
    }
}
