package model.dao;

import java.sql.ResultSet;
import java.util.List;

import model.seletor.ProdutoSeletor;
import model.vo.Produto;

public class ProdutoDAO {

	public String inserir(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	public String atualizar(Produto produto) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Produto> listarComSeletor(ProdutoSeletor seletor) {
		// TODO Auto-generated method stub
		return null;
	}

	private String criarFiltros(ProdutoSeletor seletor, String sql) {
		sql += " where ";
		boolean primeiro = true;

		return sql;
	}

	private Produto construirProdutoDoResultSet(ResultSet result) {
		// TODO criar depois de criar o banco
		return null;
	}

	public boolean existeCodBar() {
		// TODO Auto-generated method stub
		return false;
	}

	public String excluir(int produtoSelecionado) {
		// TODO Auto-generated method stub
		return null;
	}

}
