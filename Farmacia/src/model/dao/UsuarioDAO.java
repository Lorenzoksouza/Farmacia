package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.vo.Usuario;

public class UsuarioDAO {

	public String salvarUsuario(Usuario u) {
		String mensagem = "";
		String sql = "INSERT INTO USUARIO(NOME, DT_CADASTRO, LOGIN, SENHA, ID_NIVEL)" + " VALUES (?,NOW(),?,?,?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			prepStmt.setString(1, u.getNome());
			prepStmt.setString(2, u.getLogin());
			prepStmt.setString(3, u.getSenha());
			prepStmt.setInt(4, u.getNivel().getId());

			prepStmt.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar usuário. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return mensagem;
	}

	public boolean validarUsuario() {
		// TODO Auto-generated method stub

		return false;
	}

}
