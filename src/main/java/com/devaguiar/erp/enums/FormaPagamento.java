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
    public static boolean containsValue(String valor) {
        for (FormaPagamento valueEnumeration : values()) {
            if (valueEnumeration.getValor().equalsIgnoreCase(valor.trim())) {
                return true;
            }
        }
        return false;
    }
}
