package model.vo;

import java.util.Date;

public class Remedio extends Mercadoria {

	private String dosagem;
	private String composicao;
	private String tipo;
	private boolean generico;
	private String laboratorio;

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
	}

	public String getComposicao() {
		return composicao;
	}

	public void setComposicao(String composicao) {
		this.composicao = composicao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isGenerico() {
		return generico;
	}

	public void setGenerico(boolean generico) {
		this.generico = generico;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public Remedio(String codBarra, String nome, Date dataCadastro, double preco, int estoque, String dosagem,
			String composicao, String tipo, boolean generico, String laboratorio) {
		super(codBarra, nome, dataCadastro, preco, estoque);
		this.dosagem = dosagem;
		this.composicao = composicao;
		this.tipo = tipo;
		this.generico = generico;
		this.laboratorio = laboratorio;
	}

	public Remedio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Remedio(String codBarra, String nome, Date dataCadastro, double preco, int estoque) {
		super(codBarra, nome, dataCadastro, preco, estoque);
		// TODO Auto-generated constructor stub
	}

}
