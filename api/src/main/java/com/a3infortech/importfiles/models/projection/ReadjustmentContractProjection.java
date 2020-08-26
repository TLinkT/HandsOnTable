package com.a3infortech.importfiles.models.projection;

import com.a3infortech.importfiles.models.TipoPagamentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadjustmentContractProjection {

    private String numeroContrato;
    private String nomeContratante;
    private String CPF;
    private Integer qtdBeneficiarios;
    private BigDecimal valorContrato;
    private String statusContrato;
    private BigDecimal valorReajustado;
    private BigDecimal valorDescontado;
    private TipoPagamentoEnum tipoPagamento;
    private String observacao;
    private Long convenioId;


    @Override
    public String toString() {
        return "{\"ContratoReajusteProjection\":{"
                + " \"numeroContrato\":\"" + numeroContrato + "\""
                + ", \"nomeContratante\":\"" + nomeContratante + "\""
                + ", \"CPF\":\"" + CPF + "\""
                + ", \"qtdBeneficiarios\":\"" + qtdBeneficiarios + "\""
                + ", \"valorContrato\":\"" + valorContrato + "\""
                + ", \"statusContrato\":\"" + statusContrato + "\""
                + ", \"valorReajustado\":\"" + valorReajustado + "\""
                + ", \"valorDescontado\":\"" + valorDescontado + "\""
                + ", \"observacao\":\"" + observacao + "\""
                + ", \"convenioId\":\"" + convenioId + "\""
                + "}}";
    }
}
