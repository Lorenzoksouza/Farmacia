package controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.bo.RemedioBO;
import model.seletor.RemedioSeletor;
import model.vo.FormaUso;
import model.vo.Laboratorio;
import model.vo.Remedio;

public class ControllerRemedio {

	private RemedioBO remedioBO = new RemedioBO();

	public String salvar(Remedio remedio) {
		String mensagem = "";
		String validacao = validarRemedio(remedio);

		if (validacao == "") {
			if (remedioBO.existeCodBar(Integer.parseInt(remedio.getCodBarra()))) {
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
			if (remedio.getCodBarra().trim().equals("")) {
				validacao = "Remedio deve possuir um codigo";
			}
			if (remedio.getNome() == null || remedio.getNome().trim().equals("")) {
				validacao = "Nome do remedio � obrigatorio";
			}
			if (remedio.getComposicao() == null || remedio.getComposicao().trim().equals("")) {
				validacao = "Composi��o do remedio � obrigatorio";
			}
			if (remedio.getDosagem() == null || remedio.getDosagem().trim().equals("")) {
				validacao = "Dosagem do remedio � obrigatorio";
			}
			if (remedio.getFormaUso() == "") {
				validacao = "Forma de uso deve ser selecionado";
			}
			if (remedio.getLaboratorio() == null || remedio.getLaboratorio().trim().equals("")) {
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

	public boolean existeRemedioPorCodBar(int remedioSelecionado) {
		return remedioBO.existeCodBar(remedioSelecionado);
	}

	public ArrayList<FormaUso> consultarFormaUso() {
		return remedioBO.consultarFormaUso();
	}

	public ArrayList<Laboratorio> consultarLaboratorio() {
		return remedioBO.consultarLaboratorio();
	}
}
