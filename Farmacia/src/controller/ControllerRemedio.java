package controller;

import java.util.List;

import javax.swing.JOptionPane;

import model.bo.RemedioBO;
import model.seletor.RemedioSeletor;
import model.vo.Remedio;

public class ControllerRemedio {

	private RemedioBO remedioBO = new RemedioBO();

	public String salvar(Remedio remedio) {
		String mensagem = "";
		String validacao = validarRemedio(remedio);

		if (validacao == "") {
			if (remedio.existeCodBar()) {
				JOptionPane.showConfirmDialog(null,
						"Remedio ja cadastrado, deseja fazer alteração com os dados inseridos?", "",
						JOptionPane.OK_CANCEL_OPTION);
				if (remedioBO.atualizar(remedio) == "") {
					mensagem = "Remedio atualizado com sucesso";
				} else {
					mensagem = "ERRO ao atualizar remedio";
				}
			} else {
				// INSERT
				if (remedioBO.inserir(remedio) == "") {
					mensagem = "Remedio cadastrado com sucesso";
				} else {
					mensagem = "ERRO ao cadastrar remedio";
				}
			}
		}
		return mensagem;
	}

	public String validarRemedio(Remedio remedio) {
		String validacao = "";

		if (remedio == null) {
			validacao = "Remédio esta vazio";
		} else {
			// validações de campo vazio e nulo
			if (remedio.getNome().trim().equals("") || remedio.getNome() == null) {
				validacao = "Nome do remedio é obrigatorio";
			}
			if (remedio.getPreco() <= 0.0) {
				validacao = "Preço do remedio deve ser maior que zero";
			}
			if (remedio.getDosagem().trim().equals("") || remedio.getDosagem() == null) {
				validacao = "Dosagem do remedio";
			}
		}
		return validacao;
	}

	public List<Remedio> listarRemedios(RemedioSeletor seletor) {
		return remedioBO.listarRemedios(seletor);
	}

}
