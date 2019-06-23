package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dto.VendaDTO;
import model.seletor.MercadoriaSeletor;
import model.vo.Mercadoria;
import model.vo.Produto;
import model.vo.Remedio;
import model.vo.Venda;

public class VendaDAO {

	public boolean inserirVenda(double valorTotal) {
		String sql = "INSERT INTO VENDA(DT_VENDA, VALOR_TOTAL) VALUES (NOW(), ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		boolean result;
		try {
			prepStmt.setDouble(1, valorTotal);
			prepStmt.execute();
			result = true;
		} catch (SQLException e) {
			System.out.println("Erro ao inserir venda. Causa: " + e.getMessage());
			result = false;
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return result;
	}

	public List<VendaDTO> listarVendaDTO(MercadoriaSeletor seletor) {
		String sql = "SELECT R.COD_BARRA, R.NM_REMEDIO as 'NOME', R.PRECO, R.ESTOQUE FROM REMEDIO R ";

		if (seletor.temFiltro()) {
			criarFiltrosRemedio(seletor, sql);
			sql += "UNION SELECT P.COD_BARRA, P.NM_PRODUTO as 'NOME', P.PRECO, P.ESTOQUE FROM PRODUTO P";
			criarFiltrosProduto(seletor, sql);
		} else {
			sql += "UNION SELECT P.COD_BARRA, P.NM_PRODUTO as 'NOME', P.PRECO, P.ESTOQUE FROM PRODUTO P";
		}

		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<VendaDTO> vendasDTO = new ArrayList<VendaDTO>();

		try {
			resultado = stmt.executeQuery(sql);
			while (resultado.next()) {
				VendaDTO dto = new VendaDTO();

				dto.setId(resultado.getString("COD_BARRA"));
				dto.setNome(resultado.getString("NOME"));
				dto.setPreco(resultado.getDouble("PRECO"));
				dto.setEstoque(resultado.getInt("ESTOQUE"));
				vendasDTO.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar as mercadorias!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return vendasDTO;
	}

	private String criarFiltrosRemedio(MercadoriaSeletor seletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;

		if (!seletor.getCodBar().equals("")) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "R.COD_BARRA = " + seletor.getCodBar();
			primeiro = false;
		}
		if ((seletor.getNome() != null) && (seletor.getNome().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "R.NM_REMEDIO LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		return sql;
	}

	private String criarFiltrosProduto(MercadoriaSeletor seletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;

		if (!seletor.getCodBar().equals("")) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "P.COD_BARRA = " + seletor.getCodBar();
			primeiro = false;
		}
		if ((seletor.getNome() != null) && (seletor.getNome().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "P.NM_PRODUTO LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		return sql;
	}

	public List<Mercadoria> listarMercadorias(MercadoriaSeletor seletor) {
		String sqlRemedio = "SELECT * FROM REMEDIO R";
		String sqlProduto = "SELECT * FROM PRODUTO P";

		if (seletor.temFiltro()) {
			sqlRemedio = criarFiltrosRemedio(seletor, sqlRemedio);
			sqlProduto = criarFiltrosProduto(seletor, sqlProduto);
		}

		if (seletor.temPaginacao()) {
			sqlRemedio += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
			sqlProduto += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<Mercadoria> mercadorias = new ArrayList<Mercadoria>();

		try {
			resultado = stmt.executeQuery(sqlRemedio);
			while (resultado.next()) {
				Remedio remedio = new Remedio();

				remedio.setCodBarra(resultado.getString("COD_BARRA"));
				remedio.setNome(resultado.getString("NM_REMEDIO"));
				remedio.setPreco(resultado.getDouble("PRECO"));
				remedio.setEstoque(resultado.getInt("ESTOQUE"));
				mercadorias.add(remedio);
			}
			resultado = stmt.executeQuery(sqlProduto);
			while (resultado.next()) {
				Produto produto = new Produto();

				produto.setCodBarra(resultado.getString("COD_BARRA"));
				produto.setNome(resultado.getString("NM_PRODUTO"));
				produto.setPreco(resultado.getDouble("PRECO"));
				produto.setEstoque(resultado.getInt("ESTOQUE"));
				mercadorias.add(produto);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar as mercadorias!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return mercadorias;
	}

	public Venda pegarUltimaVenda() {
		String sql = "SELECT * FROM VENDA ORDER BY ID_VENDA DESC LIMIT 1";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		Venda v = new Venda();

		try {
			ResultSet result = prepStmt.executeQuery();
			while (result.next()) {
				v.setIdVenda(result.getInt(1));
				v.setDataVenda(result.getDate(2));
				v.setValor(result.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

}