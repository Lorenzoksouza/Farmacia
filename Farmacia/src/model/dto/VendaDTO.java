package model.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.seletor.MercadoriaSeletor;
import model.vo.Mercadoria;

public class VendaDTO {

	public List<Mercadoria> listarMercadoriasDTO(MercadoriaSeletor seletor) {
		String sql = "";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		// test

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<Mercadoria> listaMercadorias = new ArrayList<Mercadoria>();

		try {
			resultado = stmt.executeQuery(sql);
			while (resultado.next()) {

				// listaMercadorias.add();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar as mercadorias!!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaMercadorias;
	}

	private String criarFiltros(MercadoriaSeletor seletor, String sql) {
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

}
