package model.bo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import model.vo.Remedio;

public class GeradorDePlanilha {

	public void gerarPlanilhaRemedios(List<Remedio> remedios, String caminho) {
		String[] colunasDaPlanilha = { "Código de Barras", "Dosagem", "Composição", "Genérico", "Nome", "Data Cad.",
				"Preço", "Estoque", "Forma Uso", "Laboratório" };

		HSSFWorkbook planilha = new HSSFWorkbook();

		HSSFSheet abaPlanilha = planilha.createSheet("Remédios");

		Row headerRow = abaPlanilha.createRow(0);

		for (int i = 0; i < colunasDaPlanilha.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(colunasDaPlanilha[i]);
		}

		int rowNum = 1;
		for (Remedio rem : remedios) {
			Row novaLinha = abaPlanilha.createRow(rowNum++);

			novaLinha.createCell(0).setCellValue(rem.getCodBarra());
			novaLinha.createCell(1).setCellValue(rem.getDosagem());
			novaLinha.createCell(2).setCellValue(rem.getComposicao());
			String isGenerico = "";
			if (rem.isGenerico()) {
				isGenerico = "Sim";
			} else {
				isGenerico = "Não";
			}
			novaLinha.createCell(3).setCellValue(isGenerico);
			novaLinha.createCell(4).setCellValue(rem.getNome());
			novaLinha.createCell(5).setCellValue(rem.getDataCadastro());
			novaLinha.createCell(6).setCellValue(rem.getPreco());
			novaLinha.createCell(7).setCellValue(rem.getEstoque());
			novaLinha.createCell(8).setCellValue(rem.getFormaUso().getDescricao());
			novaLinha.createCell(9).setCellValue(rem.getLaboratorio().getNomeLaboratorio());
		}

		for (int i = 0; i < colunasDaPlanilha.length; i++) {
			abaPlanilha.autoSizeColumn(i);
		}

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(caminho = ".xls");
			planilha.write(fileOut);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
					planilha.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
