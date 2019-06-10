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
				+ " VALUES (?,?,?,?,?,?,?,?,?,?)";

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

			boolean codigoRetorno = prepStmt.execute();
			if (codigoRetorno == false) {
				mensagem = "Erro ao executar query de cadastro de Remédio!";
			}

			System.out.println(codigoRetorno);

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
		String sql = " SELECT * FROM REMEDIO R";

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

	public boolean existeCodBar(int codBar) {
		// TODO Auto-generated method stub
		return false;
	}

	public String excluir(int remedioSelecionado) {
		// método estava int, mas o cod de barra é varchar, verificar como será feito
		// este método
		String mensagem = "";

		String sql = " DELETE FROM REMEDIO " + " WHERE COD_BARRA = ?";

		Connection conexao = Banco.getConnection();
		PreparedStatement prepStmt = Banco.getPreparedStatement(conexao, sql);

		try {
			prepStmt.setInt(1, remedioSelecionado);

			int codigoRetorno = prepStmt.executeUpdate();
			if (codigoRetorno == 0) {
				// 1 - sucesso na execução
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

		String query = "SELECT DESCRICAO FROM FORMA_USO";
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