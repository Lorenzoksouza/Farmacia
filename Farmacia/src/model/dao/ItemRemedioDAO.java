package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.vo.ItemRemedio;

public class ItemRemedioDAO {

	public void inserirItemRemedio(ItemRemedio itemRemedio) {
		String sql = "INSERT INTO ITEM_REMEDIO(ID_VENDA, COD_BARRA, QT_REMEDIO) VALUES (?, ?, ?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			prepStmt.setInt(1, itemRemedio.getVenda().getIdVenda());
			prepStmt.setString(2, itemRemedio.getRemedio().getCodBarra());
			prepStmt.setInt(3, itemRemedio.getQuantidade());
			prepStmt.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir um item (Remédio) na venda. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
	}

}
