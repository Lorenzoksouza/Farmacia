package model.bo;

import java.util.List;

import model.dao.ProdutoDAO;
import model.seletor.ProdutoSeletor;
import model.vo.Produto;

public class ProdutoBO {

	ProdutoDAO produtoDAO = new ProdutoDAO();

	public String atualizar(Produto produto) {
		String mensagem = "";

		mensagem = produtoDAO.inserir(produto);

		return mensagem;
	}

	public String inserir(Produto produto) {

		String mensagem = "";

		mensagem = produtoDAO.atualizar(produto);

		return mensagem;
	}

	public List<Produto> listarProdutos(ProdutoSeletor seletor) {
		return produtoDAO.listarComSeletor(seletor);
	}

	public boolean existeCodBar() {
		return produtoDAO.existeCodBar();
	}

}