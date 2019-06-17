package model.dto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.dao.Banco;
import model.vo.Mercadoria;

public class VendaDTO {

	public List<Mercadoria> listarMercadoriasDTO() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<Mercadoria> listaMercadorias = new ArrayList<Mercadoria>();

		String query = "";
		try {
			resultado = stmt.executeQuery(query);
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

}
