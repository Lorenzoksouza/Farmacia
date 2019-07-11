package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.vo.Nivel;
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
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		Usuario u = new Usuario();

		String sql = "SELECT ID_USUARIO, NOME, DT_CADASTRO, LOGIN, SENHA, N.ID_NIVEL, N.DESCRICAO FROM USUARIO U JOIN NIVEL N ON U.ID_NIVEL = N.ID_NIVEL WHERE LOGIN = '"
				+ login + "' AND SENHA = '" + senha + "'";

		try {
			resultado = stmt.executeQuery(sql);
			if (resultado.next()) {
				u.setId(resultado.getInt("ID_USUARIO"));
				u.setNome(resultado.getString("NOME"));
				u.setDt_cadastro(resultado.getDate("DT_CADASTRO"));
				u.setLogin(resultado.getString("LOGIN"));
				u.setSenha(resultado.getString("SENHA"));

				Nivel n = new Nivel();
				n.setId(resultado.getInt("ID_NIVEL"));
				n.setDescricao(resultado.getString("DESCRICAO"));

				u.setNivel(n);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao verificar login e senha do usuário. Causa :" + e.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return u;
	}

}
