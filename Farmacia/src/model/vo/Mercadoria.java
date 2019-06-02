package model.vo;

import java.util.Date;

public abstract class Mercadoria {

	private int codBarra;
	private String nome;
	private Date dataCadastro;
	private double preco;
	private int estoque;

	public int getCodBarra() {
		return codBarra;
	}

	public void setCodBarra(int codBarra) {
		this.codBarra = codBarra;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Mercadoria(int codBarra, String nome, Date dataCadastro, double preco, int estoque) {
		super();
		this.codBarra = codBarra;
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.preco = preco;
		this.estoque = estoque;
	}

	public Mercadoria() {
		super();
		// TODO Auto-generated constructor stub
	}
}
