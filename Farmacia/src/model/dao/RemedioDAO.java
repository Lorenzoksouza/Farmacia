package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.seletor.RemedioSeletor;
import model.vo.FormaUso;
import model.vo.Laboratorio;
import model.vo.Remedio;

public class RemedioDAO {

	public String inserir(Remedio r) {
		String mensagem = "";
		String sql = "INSERT INTO REMEDIO(COD_BARRA, DOSAGEM, COMPOSICAO, GENERICO, NM_REMEDIO, DT_CADASTRO, PRECO, ESTOQUE, ID_FORMA_USO, ID_LABORATORIO)"
				+ " VALUES (?,?,?,?,?,NOW(),?,?,?,?)";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			prepStmt.setString(1, r.getCodBarra());
			prepStmt.setString(2, r.getDosagem());
			prepStmt.setString(3, r.getComposicao());
			prepStmt.setBoolean(4, r.isGenerico());
			prepStmt.setString(5, r.getNome());
			prepStmt.setDouble(6, r.getPreco());
			prepStmt.setInt(7, r.getEstoque());
			prepStmt.setInt(8, r.getFormaUso().getIdFormaUso());
			prepStmt.setInt(9, r.getLaboratorio().getIdLaboratorio());

			prepStmt.execute();

		} catch (SQLException e) {
			System.out.println("Erro ao inserir remédio. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return mensagem;
	}

	public String atualizar(Remedio r) {
		String mensagem = "";
		String sql = "UPDATE REMEDIO R SET COD_BARRA =?, DOSAGEM =?, COMPOSICAO=?, GENERICO=?, NM_REMEDIO=?, DT_CADASTRO=?, PRECO=?, ESTOQUE=?, ID_FORMA_USO=?, ID_LABORATORIO=?)"
				+ "WHERE R.COD_BARRA=?";

		Connection conn = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);

		try {
			prepStmt.setString(1, r.getCodBarra());
			prepStmt.setString(2, r.getDosagem());
			prepStmt.setString(3, r.getComposicao());
			prepStmt.setBoolean(4, r.isGenerico());
			prepStmt.setString(5, r.getNome());
			prepStmt.setDate(6, (Date) r.getDataCadastro());
			prepStmt.setDouble(7, r.getPreco());
			prepStmt.setInt(8, r.getEstoque());
			prepStmt.setInt(9, r.getFormaUso().getIdFormaUso());
			prepStmt.setInt(10, r.getLaboratorio().getIdLaboratorio());

			prepStmt.execute();

			int codigoRetorno = prepStmt.executeUpdate();
			if (codigoRetorno == 0) {
				mensagem = "Erro ao executar query de atualização de Remédio!";
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar remédio. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conn);
		}
		return mensagem;
	}

	public List<Remedio> listarComSeletor(RemedioSeletor seletor) {
		String sql = " SELECT R.COD_BARRA, R.DOSAGEM, R.COMPOSICAO, R.GENERICO, R.NM_REMEDIO, R.DT_CADASTRO,R.PRECO, R.ESTOQUE, FU.DESCRICAO, L.NM_LABORATORIO "
				+ " FROM REMEDIO R JOIN FORMA_USO FU ON R.ID_FORMA_USO = FU.ID_FORMA_USO"
				+ " JOIN LABORATORIO L ON R.ID_LABORATORIO = L.ID_LABORATORIO ";

		if (seletor.temFiltro()) {
			sql = criarFiltros(seletor, sql);
		}

		if (seletor.temPaginacao()) {
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
		// TODO FALTA FILTRO DE FORMA_USO
		// FALTA AJUSTAR FILTRO DE GENÉRICO
		sql += " WHERE ";
		boolean primeiro = true;

		if ((seletor.getCodBar() != null) && (seletor.getCodBar().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "R.COD_BARRA LIKE '%" + seletor.getCodBar().trim() + "%'";
			primeiro = false;
		}
		if ((seletor.getNomeRemedio() != null) && (seletor.getNomeRemedio().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "R.NM_REMEDIO LIKE '%" + seletor.getNomeRemedio().trim() + "%'";
			primeiro = false;
		}
		if ((seletor.getTipoRemedio() != null) && (seletor.getTipoRemedio().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "FU.DESCRICAO LIKE '%" + seletor.getTipoRemedio().trim() + "%'";
		}

		if ((seletor.getComposicaoRemedio() != null) && (seletor.getComposicaoRemedio().trim().length() > 0)) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += " R.COMPOSICAO LIKE '%" + seletor.getComposicaoRemedio().trim() + "%'";
		}

		if (seletor.isGenerico()) {
			if (!primeiro) {
				sql += " AND ";
			}
			sql += "R.GENERICO = 1";
			// Aqui estava true antes
			primeiro = false;
		}
		// Verificando o que retorna nos filtros
		// System.out.println(sql);
		return sql;
	}

	private Remedio construirProdutoDoResultSet(ResultSet result) {
		Remedio r = new Remedio();
		FormaUso fu = new FormaUso();
		Laboratorio l = new Laboratorio();

		try {
			r.setCodBarra(result.getString("COD_BARRA"));
			r.setDosagem(result.getString("DOSAGEM"));
			r.setComposicao(result.getString("COMPOSICAO"));
			r.setGenerico(result.getBoolean("GENERICO"));
			r.setNome(result.getString("NM_REMEDIO"));
			r.setPreco(result.getDouble("PRECO"));
			r.setEstoque(result.getInt("ESTOQUE"));

			// r.getFormaUso().setIdFormaUso(result.getInt("ID_FORMA_USO"));
			// r.getLaboratorio().setIdLaboratorio(result.getInt("ID_LABORATORIO"));
			fu.setDescricao(result.getString("DESCRICAO"));
			r.setFormaUso(fu);
			l.setNomeLaboratorio(result.getString("NM_LABORATORIO"));
			r.setLaboratorio(l);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return r;
	}

	public boolean existeCodBar(String codBar) {
		boolean codigoRetorno = false;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String sql = "SELECT COD_BARRA, NM_REMEDIO, PRECO, ESTOQUE FROM REMEDIO WHERE COD_BARRA = '" + codBar + "'";

		try {
			resultado = stmt.executeQuery(sql);
			if (resultado.next()) {
				codigoRetorno = true;
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao executar Query que verifica existência de Código de Barras. Causa :" + e.getMessage());
			codigoRetorno = false;
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return codigoRetorno;
	}

	public String excluir(String remedioSelecionado) {
		String mensagem = "";
		String sql = " DELETE FROM REMEDIO " + " WHERE COD_BARRA = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setString(1, remedioSelecionado);

			int codigoRetorno = prepStmt.executeUpdate();
			if (codigoRetorno == 0) {
				mensagem = "Erro ao executar a query de exclusão de remédio!";
			}
		} catch (SQLException e) {
			System.out.println("Erro ao remover remédio. Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(prepStmt);
			Banco.closeConnection(conexao);
		}
		return mensagem;
	}

	public ArrayList<FormaUso> consultarFormaUso() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<FormaUso> listaFormaUso = new ArrayList<FormaUso>();

		String query = "SELECT * FROM FORMA_USO";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				FormaUso f = new FormaUso();
				f.setIdFormaUso(Integer.parseInt(resultado.getString(1)));
				f.setDescricao(resultado.getString(2));
				listaFormaUso.add(f);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar as Formas de Uso!!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaFormaUso;
	}

	public ArrayList<Laboratorio> consultarLaboratorio() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		ArrayList<Laboratorio> listaLaboratorios = new ArrayList<Laboratorio>();

		String query = "SELECT * FROM LABORATORIO";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Laboratorio lab = new Laboratorio();
				lab.setIdLaboratorio(Integer.parseInt(resultado.getString(1)));
				lab.setNomeLaboratorio(resultado.getString(2));

				listaLaboratorios.add(lab);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao listar os Laboratórios!!");
			e.printStackTrace();
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaLaboratorios;
	}
}