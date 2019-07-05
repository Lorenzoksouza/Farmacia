package model.vo;

import java.sql.Date;

public class Usuario {

	private String nome;
	private String login;
	private String senha;
	private Date dt_cadastro;
	private boolean ativo;
	private int nivel;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDt_cadastro() {
		return dt_cadastro;
	}

	public void setDt_cadastro(Date dt_cadastro) {
		this.dt_cadastro = dt_cadastro;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Usuario(String nome, String login, String senha, Date dt_cadastro, boolean ativo, int nivel) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.dt_cadastro = dt_cadastro;
		this.ativo = ativo;
		this.nivel = nivel;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

}
