package model.bo;

import java.util.List;

import model.dao.RemedioDAO;
import model.seletor.RemedioSeletor;
import model.vo.Remedio;

public class RemedioBO {

	RemedioDAO remedioDAO = new RemedioDAO();

	public String inserir(Remedio remedio) {

		String mensagem = "";

		mensagem = remedioDAO.inserir(remedio);

		return mensagem;

	}

	public String atualizar(Remedio remedio) {

		String mensagem = "";

		mensagem = remedioDAO.atualizar(remedio);

		return mensagem;
	}

	public List<Remedio> listarRemedios(RemedioSeletor seletor) {

		return remedioDAO.listarComSeletor(seletor);

	}

	public boolean existeCodBar() {
		return remedioDAO.existeCodBar();
	}

	public String excluir(int remedioSelecionado) {
		return remedioDAO.excluir(remedioSelecionado);
	}

}
