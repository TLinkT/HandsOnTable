package com.a3infortech.importfiles.services;

import com.a3infortech.importfiles.infra.DatasHoras;
import com.a3infortech.importfiles.models.*;
import com.a3infortech.importfiles.models.projection.ReadjustmentContractProjection;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.geom.Rectangle2D;
import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Service
public class ImportService {

	private static final Logger logger = LoggerFactory.getLogger(ImportService.class);
	private DatasHoras datasHoras;

	public ImportService(DatasHoras datasHoras) {
		this.datasHoras = datasHoras;
	}

	public ReturnReadjustmentFile prepareFile(MultipartFile file) {
		String extension = FilenameUtils.getExtension(file.getOriginalFilename());
		ReturnReadjustmentFile returnReadjustmentFile = new ReturnReadjustmentFile();
		returnReadjustmentFile.setHealthInsuranceNumber(getHelthInsuranceCodeByFileName(file));

		if (extension.toLowerCase().contains("pdf")) {

			try {
				returnReadjustmentFile = processaPdf(file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return returnReadjustmentFile;
	}

	private String getHelthInsuranceCodeByFileName(MultipartFile file) {
		String code = "";
		try {
			code = file.getOriginalFilename().substring(0, file.getOriginalFilename().indexOf("-")).trim();
		} catch (Exception ignored) {
		}
		return code;
	}

	private ImportReadjustmentFileType getFileType(Sheet sheet) {
		ImportReadjustmentFileType type = null;

		String cell0 = sheet.getRow(0).getCell(0) != null ? sheet.getRow(0).getCell(0).toString().toUpperCase() : null;
		String cell1 = sheet.getRow(0).getCell(1) != null ? sheet.getRow(0).getCell(1).toString().toUpperCase() : null;
		String cell2 = sheet.getRow(0).getCell(2) != null ? sheet.getRow(0).getCell(2).toString().toUpperCase() : null;
		String cell3 = sheet.getRow(0).getCell(3) != null ? sheet.getRow(0).getCell(3).toString().toUpperCase() : null;
		String cell4 = sheet.getRow(0).getCell(4) != null ? sheet.getRow(0).getCell(4).toString().toUpperCase() : null;
		String cell5 = sheet.getRow(0).getCell(5) != null ? sheet.getRow(0).getCell(5).toString().toUpperCase() : null;
		String cell6 = sheet.getRow(0).getCell(6) != null ? sheet.getRow(0).getCell(6).toString().toUpperCase() : null;
		String cell7 = sheet.getRow(0).getCell(7) != null ? sheet.getRow(0).getCell(7).toString().toUpperCase() : null;
		String cell8 = sheet.getRow(0).getCell(8) != null ? sheet.getRow(0).getCell(8).toString().toUpperCase() : null;
		String cell9 = sheet.getRow(0).getCell(9) != null ? sheet.getRow(0).getCell(9).toString().toUpperCase() : null;
		String cell10 = sheet.getRow(0).getCell(10) != null ? sheet.getRow(0).getCell(10).toString().toUpperCase()
				: null;
		String cell11 = sheet.getRow(0).getCell(11) != null ? sheet.getRow(0).getCell(11).toString().toUpperCase()
				: null;
		String cell12 = sheet.getRow(0).getCell(12) != null ? sheet.getRow(0).getCell(12).toString().toUpperCase()
				: null;
		String cell13 = sheet.getRow(0).getCell(13) != null ? sheet.getRow(0).getCell(13).toString().toUpperCase()
				: null;
		String cell14 = sheet.getRow(0).getCell(14) != null ? sheet.getRow(0).getCell(14).toString().toUpperCase()
				: null;
		String cell15 = sheet.getRow(0).getCell(15) != null ? sheet.getRow(0).getCell(15).toString().toUpperCase()
				: null;
		String cell16 = sheet.getRow(0).getCell(16) != null ? sheet.getRow(0).getCell(16).toString().toUpperCase()
				: null;
		String cell17 = sheet.getRow(0).getCell(17) != null ? sheet.getRow(0).getCell(17).toString().toUpperCase()
				: null;
		String cell18 = sheet.getRow(0).getCell(18) != null ? sheet.getRow(0).getCell(18).toString().toUpperCase()
				: null;
		String cell19 = sheet.getRow(0).getCell(19) != null ? sheet.getRow(0).getCell(19).toString().toUpperCase()
				: null;
		String cell20 = sheet.getRow(0).getCell(20) != null ? sheet.getRow(0).getCell(20).toString().toUpperCase()
				: null;
		String cell21 = sheet.getRow(0).getCell(21) != null ? sheet.getRow(0).getCell(21).toString().toUpperCase()
				: null;

		// Tipo 1
		/*
		 * if (cell0.contains("Folha") && cell1.contains("Consignatário") &&
		 * cell2.contains("Matrícula") && cell3.contains("CPF") &&
		 * cell4.contains("Nome") && cell5.contains("T. Cargo") &&
		 * cell6.contains("Contrato") && cell7.contains("Cod na Instituição") &&
		 * cell8.contains("Data") && cell9.contains("Serviço") &&
		 * cell10.contains("Verba") && cell11.contains("Prazo original") &&
		 * cell12.contains("Prazo") && cell13.contains("Valor Original") &&
		 * cell14.contains("Valor Enviado") && cell15.contains("Valor descontado") &&
		 * cell16.contains("Critica Envio") && cell17.contains("Retorno") &&
		 * cell18.contains("Periodo") && cell19.contains("Motivo") &&
		 * cell20.contains("Último período descontado") &&
		 * cell21.contains("Ajuste manual.")) { tipoImportacaoReajusteArquivo =
		 * TipoImportacaoReajusteArquivo.TIPO1; }
		 */
		if (cell0.contains("Folha".toUpperCase()) && cell1.contains("Consignatário".toUpperCase())
				&& cell2.contains("Matrícula".toUpperCase()) && cell3.contains("CPF".toUpperCase())
				&& cell4.contains("Nome".toUpperCase()) && cell5.contains("T".toUpperCase())
				&& cell6.contains("Contrato".toUpperCase()) && cell7.contains("Cod".toUpperCase())
				&& cell8.contains("Data".toUpperCase()) && cell9.contains("Serviço".toUpperCase())
				&& cell10.contains("Verba".toUpperCase()) && cell11.contains("Prazo".toUpperCase())
				&& cell12.contains("Prazo".toUpperCase()) && cell13.contains("Valor".toUpperCase())
				&& cell14.contains("Valor".toUpperCase()) && cell15.contains("Valor".toUpperCase())
				&& cell16.contains("Critica".toUpperCase()) && cell17.contains("Retorno".toUpperCase())
				&& cell18.contains("Periodo".toUpperCase()) && cell19.contains("Motivo".toUpperCase())
				&& cell20.contains("Último".toUpperCase()) && cell21.contains("Ajuste".toUpperCase())) {
			type = ImportReadjustmentFileType.TYPE1;
		}

		// Tipo 2
		if (cell0.contains("Nome Convênio".toUpperCase()) && cell1.contains("Numero Contrato".toUpperCase())
				&& cell2.contains("Contratante".toUpperCase()) && cell3.contains("CPF".toUpperCase())
				&& cell4.contains("Beneficiário".toUpperCase()) && cell5.contains("Assinatura".toUpperCase())
				&& cell6.contains("Valor".toUpperCase())) {
			type = ImportReadjustmentFileType.TYPE2;
		}

		// Tipo 3
		if (cell0.contains("") && cell1.contains("") && cell2.contains("Matrícula".toUpperCase())
				&& cell3.contains("Consignatária".toUpperCase()) && cell4.contains("CPF".toUpperCase())
				&& cell5.contains("Nome do Funcionário".toUpperCase()) && cell6.contains("Parcela".toUpperCase())
				&& cell7.contains("Verba".toUpperCase()) && cell8.contains("Valor Parcela".toUpperCase())
				&& cell9.contains("Descontado".toUpperCase()) && cell10.contains("Situação Func.".toUpperCase())
				&& cell11.contains("Situação Parcela".toUpperCase())
				&& cell12.contains("Exportado para folha".toUpperCase()) && cell13.contains("Competência".toUpperCase())
				&& cell14.contains("Data da baixa".toUpperCase()) && cell15.contains("Observação".toUpperCase())) {
			type = ImportReadjustmentFileType.TYPE3;
		}

		return type;
	}

	private List<ReadjustmentFileType1> processFileType1(Sheet sheet) {
		List<ReadjustmentFileType1> list = new ArrayList<>();
		List<ReadjustmentFileType1> cleanList = new ArrayList<>();
		ReadjustmentFileType1 file = null;
		int row = 0;

		for (Row cells : sheet) {
			file = new ReadjustmentFileType1();
			if (row > 0) {
				if (cells.getCell(0) != null) {
					file.setFolha(cells.getCell(0).toString());
				}
				if (cells.getCell(1) != null) {
					file.setConsignatario(cells.getCell(1).toString());
				}
				if (cells.getCell(2) != null) {
					file.setMatricula(cells.getCell(2).toString().replace(".0", ""));
				}
				if (cells.getCell(3) != null) {
					file.setCPF(cells.getCell(3).toString());
				}
				if (cells.getCell(4) != null) {
					file.setNome(cells.getCell(4).toString());
				}
				if (cells.getCell(5) != null) {
					file.setTCargo(cells.getCell(5).toString());
				}
				if (cells.getCell(6) != null) {
					file.setContrato(cells.getCell(6).toString().replace(".0", ""));
				}
				if (cells.getCell(7) != null) {
					file.setCodNaInstituicao(cells.getCell(7).toString().replace(".0", ""));
				} else {
					file.setCodNaInstituicao("");
				}
				if (cells.getCell(8) != null) {
					if (cells.getCell(8).getCellType() == CellType.NUMERIC) {
						file.setData(datasHoras.dateTimeToLocalDateDate(cells.getCell(8).getDateCellValue()));
					} else if (cells.getCell(8).getCellType() == CellType.STRING) {
						file.setData(datasHoras.stringDateTimeToLocalDateTime(cells.getCell(8).toString()));
					}
				}
				if (cells.getCell(9) != null) {
					file.setServico(cells.getCell(9).toString());
				}
				if (cells.getCell(10) != null) {
					file.setVerba(new BigDecimal(cells.getCell(10).toString()));
				}
				if (cells.getCell(11) != null) {
					file.setPrazoOriginal((int) Math.round(Double.parseDouble(cells.getCell(11).toString())));
				}
				if (cells.getCell(12) != null) {
					file.setPrazo((int) Math.round(Double.parseDouble(cells.getCell(12).toString())));
				}
				if (cells.getCell(13) != null) {
					file.setValorOriginal(new BigDecimal(cells.getCell(13).toString()));
				}
				if (cells.getCell(14) != null) {
					file.setValorEnviado(new BigDecimal(cells.getCell(14).toString()));
				}
				if (cells.getCell(15) != null) {
					file.setValorDescontado(new BigDecimal(cells.getCell(15).toString()));
				}
				if (cells.getCell(16) != null) {
					file.setCriticaEnvio(cells.getCell(16).toString());
				}
				if (cells.getCell(17) != null) {
					file.setRetorno(cells.getCell(17).toString());
				}
				if (cells.getCell(18) != null) {
					file.setPeriodoBloqueio(cells.getCell(18).toString());
				}
				if (cells.getCell(19) != null) {
					file.setMotivo(cells.getCell(19).toString());
				}
				if (cells.getCell(20) != null) {
					file.setUltimoPeriododescontado(cells.getCell(20).toString().replace(".0", ""));
				}
				if (cells.getCell(21) != null) {
					file.setAjusteManual(cells.getCell(21).toString());
				}

				// Verifica se tem multiplos contratos por linha
				if (file.getCodNaInstituicao().contains("/")) {
					String[] contratos = file.getCodNaInstituicao().split("/");
					int ind = 0;
					for (String codNaInstituicao : contratos) {
						if (ind == 0) {
							file.setCodNaInstituicao(codNaInstituicao);
						} else {
							file = new ReadjustmentFileType1(file, codNaInstituicao);
						}
						list.add(file);
						ind++;
					}
				} else {
					list.add(file);
				}

			}

			row++;
		}

		// Remove os CPFS Duplicados verifico se o valor descontado é diferente e soma
		// os valores descontados em um unico CPF
		list.forEach(readjustmentFileType1 -> {
			Optional<ReadjustmentFileType1> achou = cleanList.stream()
					.filter(it -> it.getCPF().equals(readjustmentFileType1.getCPF())).findFirst();

			if (achou.isEmpty()) {
				cleanList.add(readjustmentFileType1);
			} else {
				BigDecimal valor1 = achou.get().getValorDescontado();
				BigDecimal valor2 = readjustmentFileType1.getValorDescontado();

				if (valor1.compareTo(valor2) != 0) { // Iguais
					cleanList.stream().filter(arquivoTipo1 -> arquivoTipo1.getCPF().equals(achou.get().getCPF()))
							.forEach(arquivoTipo1 -> arquivoTipo1.setValorDescontado(valor1.add(valor2)));
				}
			}
		});

		return cleanList;
	}

	private List<ReadjustmentFileType2> processFileType2(Sheet sheet) {
		List<ReadjustmentFileType2> list = new ArrayList<>();
		List<ReadjustmentFileType2> cleanList = new ArrayList<>();
		ReadjustmentFileType2 file = null;
		int row = 0;

		/*
		 * Nome Convênio - Numero Contrato - Contratante - CPF - Beneficiário -
		 * Assinatura - Valor
		 */
		for (Row cells : sheet) {
			file = new ReadjustmentFileType2();
			if (row > 0) {
				if (cells.getCell(0) != null) {
					if (!cells.getCell(0).toString().equals("")) {
						file.setNomeConvenio(cells.getCell(0).toString());

						if (cells.getCell(1) != null) {
							file.setNumeroContrato(cells.getCell(1).toString().replace(".0", ""));
						}
						if (cells.getCell(2) != null) {
							file.setContratante(cells.getCell(2).toString());
						}
						if (cells.getCell(3) != null) {
							file.setCpf(cells.getCell(3).toString());
						}
						if (cells.getCell(4) != null) {
							file.setBeneficiario(cells.getCell(4).toString());
						}
						if (cells.getCell(5) != null) {
							if (cells.getCell(5).getCellType() == CellType.NUMERIC) {
								file.setAssinatura(
										datasHoras.dateTimeToLocalDateDate(cells.getCell(5).getDateCellValue()));
							} else if (cells.getCell(5).getCellType() == CellType.STRING) {
								file.setAssinatura(
										datasHoras.stringDateTimeToLocalDateTime(cells.getCell(5).toString()));
							}
						}

						if (cells.getCell(6) != null) {
							file.setValor(new BigDecimal(cells.getCell(6).toString()));
						}
					}
				}

				if (file.getNumeroContrato() != null) {
					list.add(file);
				}
			}

			row++;
		}

		// Remove os Contratos Duplicados e soma os valores descontados em um unico CPF
		list.forEach(readjustmentFileType2 -> {
			Optional<ReadjustmentFileType2> found = cleanList.stream()
					.filter(it -> it.getNumeroContrato().equals(readjustmentFileType2.getNumeroContrato())).findFirst();

			if (found.isEmpty()) {
				cleanList.add(readjustmentFileType2);
			} else {
				BigDecimal valor1 = found.get().getValor();
				BigDecimal valor2 = readjustmentFileType2.getValor();

				cleanList.stream().filter(
						arquivoTipo1 -> arquivoTipo1.getNumeroContrato().equals(found.get().getNumeroContrato()))
						.forEach(arquivoTipo1 -> arquivoTipo1.setValor(valor1.add(valor2)));
			}
		});

		return cleanList;
	}

	private List<ReajusteArquivoTipo3> processaArquivoTipo3(String sheet) {
		List<ReajusteArquivoTipo3> list = new ArrayList<>();
		List<ReajusteArquivoTipo3> cleanList = new ArrayList<>();
		ReajusteArquivoTipo3 file = null;
		int row = 0;

		/*
		 * Nome Convênio - Numero Contrato - Contratante - CPF - Beneficiário -
		 * Assinatura - Valor
		 */
		for (Row cells : sheet) {
			file = new ReajusteArquivoTipo3();
			if (row > 0) {
				if (cells.getCell(2) != null) {
					if (!cells.getCell(2).toString().equals("")) {
						file.setMatricula(cells.getCell(2).toString());

						if (cells.getCell(3) != null) {
							file.setConsignataria(cells.getCell(3).toString());
						}

						if (cells.getCell(4) != null) {
							file.setCpf(cells.getCell(4).toString());
						}

						if (cells.getCell(5) != null) {
							file.setNomeFuncionario(cells.getCell(5).toString());
						}

						if (cells.getCell(6) != null) {
							if (!cells.getCell(6).equals("")) {
								file.setParcela(Integer.valueOf(cells.getCell(6).toString().replace(".0", "")));
							}
						}

						if (cells.getCell(7) != null) {
							file.setVerba(Integer.valueOf(cells.getCell(7).toString()));
						}

						if (cells.getCell(8) != null) {
							file.setValorParcela(new BigDecimal(cells.getCell(8).toString()));
						}

						if (cells.getCell(9) != null) {
							file.setDescontado(new BigDecimal(cells.getCell(9).toString()));
						}

						if (cells.getCell(10) != null) {
							file.setSituacaoFunc(cells.getCell(10).toString());
						}

						if (cells.getCell(11) != null) {
							file.setSituacaoParcela(cells.getCell(11).toString());
						}

						if (cells.getCell(12) != null) {
							if (!cells.getCell(12).getCellType().equals(CellType.BLANK)) {
								file.setExportadoParaFolha(
										Integer.valueOf(cells.getCell(12).toString().replace(".0", "")));
							}
						}

						if (cells.getCell(13) != null) {
							file.setCompetencia(cells.getCell(13).toString());
						}

						if (cells.getCell(14) != null) {
							if (cells.getCell(14).getCellType() == CellType.NUMERIC) {
								file.setDataDaBaixa(
										datasHoras.dateTimeToLocalDateDate(cells.getCell(14).getDateCellValue()));
							} else if (cells.getCell(14).getCellType() == CellType.STRING) {
								file.setDataDaBaixa(
										datasHoras.stringDateTimeToLocalDateTime(cells.getCell(14).toString()));
							}
						}

						if (cells.getCell(15) != null) {
							file.setObservacao(cells.getCell(15).toString());
						}
					}
				}

				if (file.getCpf() != null) {
					list.add(file);
				}
			}

			row++;
		}

		// Remove as Matriculas duplicadas e soma os valores descontados em um unico CPF
		list.forEach(reajusteArquivoTipo3 -> {
			Optional<ReajusteArquivoTipo3> found = cleanList.stream()
					.filter(it -> it.getMatricula().equals(reajusteArquivoTipo3.getMatricula())).findFirst();

			if (found.isEmpty()) {
				cleanList.add(reajusteArquivoTipo3);
			} else {
				BigDecimal valor1 = found.get().getDescontado();
				BigDecimal valor2 = reajusteArquivoTipo3.getDescontado();

				cleanList.stream()
						.filter(arquivo -> reajusteArquivoTipo3.getMatricula().equals(found.get().getMatricula()))
						.forEach(arquivo -> arquivo.setDescontado(valor1.add(valor2)));
			}
		});

		return cleanList;
	}

	public ReturnReadjustmentFile processaPdf(byte[] pdf) {
		String pdfText = "";
		PDFTextStripper pdfTextStripper;
		PDFTextStripperByArea stripper;

		// Map<Integer, String[]> colunas = new HashMap<Integer, String[]>();
		Map<String, List<String>> colunasMap = new HashMap<>();
		ReturnReadjustmentFile returnReadjustmentFile = new ReturnReadjustmentFile();
		PDDocument pdDocument = null;

		try {
			stripper = new PDFTextStripperByArea();
			stripper.setSortByPosition(true);

			pdfTextStripper = new PDFTextStripper();
			PDDocument document = PDDocument.load(pdf);
			int numeroPaginas = document.getNumberOfPages();

			ArrayList<Double[]> parametros = new ArrayList<>();

			Double[] matricula = { 20d, 120d, 35d, 670d };
			Double[] nome = { 55d, 120d, 195d, 670d };
			Double[] cargo = { 250d, 120d, 100d, 670d };
			Double[] reg = { 350d, 120d, 25d, 670d };
			Double[] cpf = { 375d, 120d, 60d, 670d };
			Double[] ref = { 435d, 120d, 33d, 670d };
			Double[] lim = { 469d, 120d, 31d, 670d };
			Double[] valor = { 500d, 120d, 70d, 670d };

			parametros.add(matricula);
			parametros.add(nome);
			parametros.add(cargo);
			parametros.add(reg);
			parametros.add(cpf);
			parametros.add(ref);
			parametros.add(lim);
			parametros.add(valor);

			List<String> nomesColunas = Arrays
					.asList(new String[] { "matricula", "nome", "cargo", "REG", "cpf", "ref", "lim", "valor" });

			for (int page = 0; page < numeroPaginas; page++) {
				int chave = 0;
				for (String col : nomesColunas) {
					Double x = parametros.get(chave)[0];
					Double y = parametros.get(chave)[1];
					Double width = parametros.get(chave)[2];
					Double height = parametros.get(chave)[3];

					PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
					Rectangle2D rect = new java.awt.geom.Rectangle2D.Double(x, y, width, height);
					textStripper.addRegion(col, rect);

					PDPage docPage = document.getPage(page);

					textStripper.extractRegions(docPage);

					String textForRegion = textStripper.getTextForRegion(col);
					
					if(!colunasMap.containsKey(col)) {
						colunasMap.put(col, new ArrayList<String>());
					}
					
					colunasMap.get(col).addAll(Arrays.asList(textForRegion.replace("\r", "").split("\n")));
					
					chave++;
				}
			}

			List<ReajusteArquivoTipo4> listTipo4 = new ArrayList();
			
			ReajusteArquivoTipo4 tipo4 = new ReajusteArquivoTipo4();
			//ReflectionUtils.setField(ReajusteArquivoTipo4.class.getField(key), tipo4, "teste");
			//int row = 0;
			//for(String key : colunas.keySet()) {
			//	System.out.println(key);
			//	colunas.get(key).
			//}
			
			Field field;
			int rowIdx = 0;
			int colIdx = 0;
			for(String col: nomesColunas) {
				field = ReajusteArquivoTipo4.class.getDeclaredField(col);
				if(colunasMap.get(col).get(rowIdx) != null) {
					field.set(tipo4, colunasMap.get(col).get(rowIdx));	
				}
				//ReflectionUtils.setField(ReajusteArquivoTipo4.class.getDeclaredField(col), tipo4, colunasMap.get(col).get(0));
				System.out.println("ColUdx: " + colIdx + " Size: " + (nomesColunas.size() - 1));
				if(colIdx == nomesColunas.size() - 1) {
					System.out.println(tipo4.toString());
					colIdx = 0;		
					tipo4 = new ReajusteArquivoTipo4();
					rowIdx++;
				}
				colIdx++;
			}
			
			//for (Map.Entry<String, List<String>> list : colunas.entrySet()) {
				
				//System.out.println("========================================");
				//System.out.println(list.getKey());
				//System.out.println(list.getValue().toString());
				//System.out.println("=========================================");

			//}

		} catch (Exception e) {
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			logger.error("Erro ao ler o PDF: \n" + errors.toString());
		}

		return returnReadjustmentFile;
		// finally {
		// try {
		// pdDocument.close();
		// } catch (IOException ignored) {
		// }
		// }

		/*
		 * String pdfText = ""; PDFTextStripper pdfTextStripper; PDFTextStripperByArea
		 * stripper;
		 * 
		 * Map<Integer, String[]> colunas = new HashMap<Integer, String[]>();
		 * 
		 * ReturnReadjustmentFile returnReadjustmentFile = new ReturnReadjustmentFile();
		 * PDDocument pdDocument = null;
		 * 
		 * try { stripper = new PDFTextStripperByArea();
		 * stripper.setSortByPosition(true);
		 * 
		 * pdfTextStripper = new PDFTextStripper();
		 * 
		 * // Parameters //String filepath =
		 * "C:/Users/Levyson/Desktop/prevmedimport-master/Arquivos de Teste/0330 - SANTA RITA.PDF"
		 * ; //File pdf = new File(filepath); PDDocument document =
		 * PDDocument.load(pdf); int numeroPaginas = document.getNumberOfPages();
		 * 
		 * ArrayList<Double[]> parametros = new ArrayList<Double[]>();
		 * 
		 * Double matricula[] = { 20d, 120d, 35d, 670d }; Double nome[] = { 55d, 120d,
		 * 195d, 670d }; Double cargo[] = { 250d, 120d, 100d, 670d }; Double reg[] = {
		 * 350d, 120d, 25d, 670d }; Double cpf[] = { 375d, 120d, 60d, 670d }; Double
		 * ref[] = { 435d, 120d, 33d, 670d }; Double lim[] = { 469d, 120d, 31d, 670d };
		 * Double valor[] = { 500d, 120d, 70d, 670d };
		 * 
		 * parametros.add(matricula); parametros.add(nome); parametros.add(cargo);
		 * parametros.add(reg); parametros.add(cpf); parametros.add(ref);
		 * parametros.add(lim); parametros.add(valor);
		 * 
		 * int chave = 0;
		 * 
		 * for (int page = 0; page < numeroPaginas; page++) {
		 * 
		 * for (Double[] parametro : parametros) {
		 * 
		 * Double x = parametro[0]; Double y = parametro[1]; Double width =
		 * parametro[2]; Double height = parametro[3];
		 * 
		 * String colunasTratadas[] = null;
		 * 
		 * PDFTextStripperByArea textStripper = new PDFTextStripperByArea(); Rectangle2D
		 * rect = new java.awt.geom.Rectangle2D.Double(x, y, width, height);
		 * textStripper.addRegion("region", rect);
		 * 
		 * PDPage docPage = document.getPage(page);
		 * 
		 * textStripper.extractRegions(docPage);
		 * 
		 * String textForRegion = textStripper.getTextForRegion("region");
		 * colunasTratadas = textForRegion.split("\n");
		 * 
		 * colunas.put(chave, colunasTratadas); chave++;
		 * 
		 * // if (!pdDocument.isEncrypted()) { // pdfText =
		 * pdfTextStripper.getText(pdDocument); // pdfText.replaceAll("\t", "\n"); //
		 * returnReadjustmentFile.setType(ImportReadjustmentFileType.TYPE3); //
		 * returnReadjustmentFile.setFileName(file.getOriginalFilename()); //
		 * returnReadjustmentFile.setFileList(pdfText); // System.out.println(pdfText);
		 * // } }
		 * 
		 * } for (int coluna : colunas.keySet()) { for (String teste :
		 * colunas.get(coluna)) { System.out.print(teste); } }
		 * 
		 * } catch (Exception e) { StringWriter errors = new StringWriter();
		 * e.printStackTrace(new PrintWriter(errors));
		 * logger.error("Erro ao ler o PDF: \n" + errors.toString()); }
		 * 
		 * returnReadjustmentFile.setColunas(colunas); ; return returnReadjustmentFile;
		 * // finally { // try { // pdDocument.close(); // } catch (IOException ignored)
		 * { // } // }
		 */
	}

}