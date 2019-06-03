package model.vo;

import java.util.Date;

public class Produto extends Mercadoria {

	private String categoria;

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Produto(String codBarra, String nome, Date dataCadastro, double preco, int estoque, String categoria) {
		super(codBarra, nome, dataCadastro, preco, estoque);
		this.categoria = categoria;
	}

	public Produto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Produto(String codBarra, String nome, Date dataCadastro, double preco, int estoque) {
		super(codBarra, nome, dataCadastro, preco, estoque);
		// TODO Auto-generated constructor stub
	}

}
