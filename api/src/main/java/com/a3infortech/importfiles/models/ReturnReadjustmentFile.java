package com.a3infortech.importfiles.models;

import com.a3infortech.importfiles.models.projection.ReadjustmentContractProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnReadjustmentFile {

    private ImportReadjustmentFileType type;
    private String healthInsuranceNumber;
    private String fileName;
    private List<?> fileList;
    private List<ReadjustmentContractProjection> listaContrato;
}
