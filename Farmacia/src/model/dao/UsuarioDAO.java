package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public Usuario validarUsuario(String login, String senha) {
		boolean codigoRetorno = false;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String sql = "SELECT * FROM USUARIO WHERE LOGIN = '" + login + "' AND SENHA = '" + senha + "'";

		try {
			resultado = stmt.executeQuery(sql);
			if (resultado.next()) {
				codigoRetorno = true;
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar login e senha do usuário. Causa :" + e.getMessage());
			codigoRetorno = false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return codigoRetorno;
	}

}
