package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

	public ArrayList<String> consultarCategoria() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<String> listaCategorias = new ArrayList<String>();

		String query = "SELECT NM_CATEGORIA FROM CATEGORIA";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				listaCategorias.add(resultado.getString("NM_CATEGORIA"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao listar os Categorias!!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaCategorias;
	}

}
