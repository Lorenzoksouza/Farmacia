package model.vo;

import java.util.Date;

public class Remedio extends Mercadoria {

	private String dosagem;
	private String composicao;
	private String formaUso;
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

	public String getFormaUso() {
		return formaUso;
	}

	public void setFormaUso(String formaUso) {
		this.formaUso = formaUso;
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
		this.formaUso = tipo;
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
