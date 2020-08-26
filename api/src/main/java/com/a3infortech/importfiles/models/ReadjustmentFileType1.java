package com.a3infortech.importfiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadjustmentFileType1 {
    private String folha;
    private String consignatario;
    private String matricula;
    private String CPF;
    private String nome;
    private String tCargo;
    private String contrato;
    private String codNaInstituicao;
    private LocalDateTime data;
    private String servico;
    private BigDecimal verba;
    private Integer prazoOriginal;
    private Integer prazo;
    private BigDecimal valorOriginal;
    private BigDecimal valorEnviado;
    private BigDecimal valorDescontado;
    private String criticaEnvio;
    private String retorno;
    private String periodoBloqueio;
    private String motivo;
    private String ultimoPeriododescontado;
    private String ajusteManual;

    public ReadjustmentFileType1(ReadjustmentFileType1 readjustmentFileType1, String codNaInstituicao) {
        setFolha(readjustmentFileType1.getFolha());
        setConsignatario(readjustmentFileType1.getConsignatario());
        setMatricula(readjustmentFileType1.getMatricula());
        setCPF(readjustmentFileType1.getCPF());
        setNome(readjustmentFileType1.getNome());
        setTCargo(readjustmentFileType1.getTCargo());
        setContrato(readjustmentFileType1.getContrato());
        setCodNaInstituicao(codNaInstituicao);
        setData(readjustmentFileType1.getData());
        setServico(readjustmentFileType1.getServico());
        setVerba(readjustmentFileType1.getVerba());
        setPrazoOriginal(readjustmentFileType1.getPrazoOriginal());
        setPrazo(readjustmentFileType1.getPrazo());
        setValorOriginal(readjustmentFileType1.getValorOriginal());
        setValorEnviado(readjustmentFileType1.getValorEnviado());
        setValorDescontado(readjustmentFileType1.getValorDescontado());
        setCriticaEnvio(readjustmentFileType1.getCriticaEnvio());
        setRetorno(readjustmentFileType1.getRetorno());
        setPeriodoBloqueio(readjustmentFileType1.getPeriodoBloqueio());
        setMotivo(readjustmentFileType1.getMotivo());
        setUltimoPeriododescontado(readjustmentFileType1.getUltimoPeriododescontado());
        setAjusteManual(readjustmentFileType1.getAjusteManual());
    }
}
