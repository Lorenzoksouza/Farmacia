package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.seletor.RemedioSeletor;
import model.vo.Remedio;

public class RemedioDAO {

	public String inserir(Remedio remedio) {

		return null;
	}

	public String atualizar(Remedio remedio) {

		return null;
	}

	public List<Remedio> listarComSeletor(RemedioSeletor seletor) {
		String sql = " SELECT * FROM remedio r";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
			// TODO continuar...
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}
		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Remedio> remedios = new ArrayList<Remedio>();

		try {
			ResultSet result = prepStmt.executeQuery();

			while (result.next()) {
				Remedio r = construirProdutoDoResultSet(result);
				remedios.add(r);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return remedios;
	}

	private String criarFiltros(RemedioSeletor seletor, String sql) {
		sql += " where ";
		boolean primeiro = true;

		if (seletor.getCodBar() > 0) {
			if (!primeiro) {
				sql += " and ";
			}
			sql += "r.id = " + seletor.getCodBar();
			primeiro = false;
		}
		if ((seletor.getNomeRemedio() != null) && (seletor.getNomeRemedio().trim().length() > 0)) {
			if (!primeiro) {
				sql += " and ";
			}
			sql += "r.nome like '% " + seletor.getNomeRemedio() + "%'";
			primeiro = false;
		}
		if (seletor.isGenerico()) {
			if (!primeiro) {
				sql += " and ";
			}
			sql += "r.generico = true";
			primeiro = true;
		}
		return sql;
	}

	private Remedio construirProdutoDoResultSet(ResultSet result) {
		// TODO criar depois de criar o banco
		return null;
	}
}
