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

}
