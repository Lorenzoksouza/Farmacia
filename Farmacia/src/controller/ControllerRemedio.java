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
			if (remedioBO.existeCodBar()) {
				// UPDATE
				int opcao = JOptionPane.showConfirmDialog(null,
						"Remedio ja cadastrado, deseja fazer altera��o com os dados inseridos?", "",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcao == 0) {
					if (remedioBO.atualizar(remedio) == "") {
						mensagem = "Remedio atualizado com sucesso";
					} else {
						mensagem = "ERRO ao atualizar remedio";
					}
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

	private String validarRemedio(Remedio remedio) {
		String validacao = "";

		if (remedio == null) {
			validacao = "Rem�dio esta vazio";
		} else {
			// valida��es de campo vazio e nulo
			if (remedio.getCodBarra() == 0) {
				validacao = "Remedio deve possuir um codigo";
			}
			if (remedio.getNome().trim().equals("") || remedio.getNome() == null) {
				validacao = "Nome do remedio � obrigatorio";
			}
			if (remedio.getComposicao().trim().equals("") || remedio.getComposicao() == null) {
				validacao = "Composi��o do remedio � obrigatorio";
			}
			if (remedio.getDosagem().trim().equals("") || remedio.getDosagem() == null) {
				validacao = "Dosagem do remedio � obrigatorio";
			}
			if (remedio.getTipo() == "") {
				validacao = "Tipo deve ser selecionado";
			}
			if (remedio.getLaboratorio().trim().equals("") || remedio.getLaboratorio() == null) {
				validacao = "Dosagem do remedio � obrigatorio";
			}
			if (remedio.getPreco() <= 0.0) {
				validacao = "Pre�o do remedio deve ser maior que zero";
			}

		}
		return validacao;
	}

	public List<Remedio> listarRemedios(RemedioSeletor seletor) {
		return remedioBO.listarRemedios(seletor);
	}

	public String excluir(int remedioSelecionado) {
		return remedioBO.excluir(remedioSelecionado);
	}

}
