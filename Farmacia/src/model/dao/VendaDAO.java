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
import model.vo.Venda;

public class VendaDAO {

	public Venda inserirVenda(double valorTotal) {
		Venda venda = new Venda();
		String sql = "INSERT INTO VENDA(DT_VENDA, VALOR_TOTAL) VALUES (NOW(), ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			prepStmt.setDouble(1, valorTotal);
			prepStmt.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir venda. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return venda;
	}

	public List<VendaDTO> listarMercadorias(MercadoriaSeletor seletor) {
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

		if (seletor.getCodBar() != "") {
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
			sql += "R.NM_REMEDIO LIKE '% " + seletor.getNome() + "%'";
			primeiro = false;
		}

		return sql;
	}

	private String criarFiltrosProduto(MercadoriaSeletor seletor, String sql) {
		sql += " WHERE ";
		boolean primeiro = true;

		if (seletor.getCodBar() != "") {
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
			sql += "P.NM_PRODUTO LIKE '% " + seletor.getNome() + "%'";
			primeiro = false;
		}

		return sql;
	}

}