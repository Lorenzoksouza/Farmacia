package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}