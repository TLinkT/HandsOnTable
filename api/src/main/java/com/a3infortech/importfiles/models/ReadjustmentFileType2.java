package com.a3infortech.importfiles.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadjustmentFileType2 {

    private String nomeConvenio;
    private String numeroContrato;
    private String contratante;
    private String cpf;
    private String beneficiario;
    private LocalDateTime assinatura;
    private BigDecimal valor;

}
