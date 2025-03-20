package com.devaguiar.erp.enums;

public enum FormaPagamento {
    DINHEIRO("Dinheiro"),
    CARTAO("Cartão"),
    BOLETO("Boleto"),
    PIX("Pix");

    private String valor;

    FormaPagamento(String valor) {
        this.valor = valor;
    }
}
