package com.a3infortech.importfiles.models;

public enum TipoPagamentoEnum {

    bob("Boleto bancário"),
    cac("Cartão de crédito"),
    car("Cartão recorrente"),
    def("Desconto em folha"),
    emp("Empresa"),
    avu("Avulso");

    private String descricao;

    TipoPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
