package model.vo;

import java.sql.Date;

public class Usuario {

	private int cpf;
	private String nome;
	private String login;
	private String senha;
	private Date dt_cadastro;
	private Date dt_nascimento;
	private boolean ativo;
	private int nivel;

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

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

	public Date getDt_nascimento() {
		return dt_nascimento;
	}

	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
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

	public Usuario(int cpf, String nome, String login, String senha, Date dt_cadastro, Date dt_nascimento,
			boolean ativo, int nivel) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.dt_cadastro = dt_cadastro;
		this.dt_nascimento = dt_nascimento;
		this.ativo = ativo;
		this.nivel = nivel;
	}

	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

}
