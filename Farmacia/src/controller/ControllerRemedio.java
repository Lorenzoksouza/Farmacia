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
						"Remedio já cadastrado, deseja fazer alteração com os dados inseridos?", "",
						JOptionPane.OK_CANCEL_OPTION);
				if (opcao == 0) {
					if (remedioBO.atualizar(remedio) == "") {
						mensagem = "Remédio atualizado com sucesso.";
					} else {
						mensagem = "Erro ao atualizar Remédio.";
					}
				}
			} else {
				// INSERT
				if (remedioBO.inserir(remedio) == "") {
					mensagem = "Remédio cadastrado com sucesso!";
				} else {
					mensagem = "Erro ao inserir Remédio.";
				}
			}
		}
		return mensagem;
	}

	private String validarRemedio(Remedio remedio) {
		String validacao = "";

		if (remedio == null) {
			validacao = "Remédio esta vazio";
		} else {
			// validações de campo vazio e nulo
			if (remedio.getCodBarra().trim().equals("")) {
				validacao = "Remedio deve possuir um código de barras.";
			}
			if (remedio.getNome() == null || remedio.getNome().trim().equals("")) {
				validacao = "Nome do remédio é obrigatório.";
			}
			if (remedio.getComposicao() == null || remedio.getComposicao().trim().equals("")) {
				validacao = "Composição do remedio é obrigatório.";
			}
			if (remedio.getDosagem() == null || remedio.getDosagem().trim().equals("")) {
				validacao = "Dosagem do remédio é obrigatória.";
			}
			if (remedio.getFormaUso().getDescricao() == "") {
				validacao = "Forma de uso deve ser selecionado.";
			}
			if (remedio.getLaboratorio() == null || remedio.getLaboratorio().getNomeLaboratorio().trim().equals("")) {
				validacao = "Laboratório deve ser selecionado.";
			}
			if (remedio.getPreco() <= 0.0) {
				validacao = "Preço do remédio deve ser maior que zero.";
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
