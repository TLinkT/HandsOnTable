package com.a3infortech.importfiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReajusteArquivoTipo3 {

    private String matricula;
    private String consignataria;
    private String cpf;
    private String nomeFuncionario;
    private Integer parcela;
    private Integer verba;
    private BigDecimal valorParcela;
    private BigDecimal descontado;
    private String situacaoFunc;
    private String situacaoParcela;
    private Integer exportadoParaFolha;
    private String competencia;
    private LocalDateTime dataDaBaixa;
    private String observacao;

}
