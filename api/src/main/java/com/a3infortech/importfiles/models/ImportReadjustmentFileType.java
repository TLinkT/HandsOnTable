package com.a3infortech.importfiles.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImportReadjustmentFileType {
    /*Folha	Consignatário	Matrícula	CPF	Nome	T. Cargo	Contrato	Cod na Instituição	Data	Serviço	Verba	Prazo original	Prazo	Valor Original	Valor Enviado	Valor descontado	Critica Envio	Retorno	Periodo Bloqueio	Motivo	Último período descontado	Ajuste manual.*/
    TYPE1(1),
    /*Nome Convênio	- Numero Contrato - Contratante - CPF - Beneficiário - Assinatura - Valor*/
    TYPE2(2),
    /*Matrícula - Consignatária - CPF - Nome do Funcionário - Parcela - Verba - Valor Parcela - Descontado - Situação Func. - Situação Parcela - Exportado para folha - Competência - Data da baixa - Observação*/
    TYPE3(3);

    private int type;

}
