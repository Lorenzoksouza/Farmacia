package model.vo;

public class Remedio {

	private int idRemedio;
	private String nome;
	private double preco;
	private String dosagem;
	private String tipo;
	private boolean generico;
	private int estoque;

	public int getIdRemedio() {
		return idRemedio;
	}

	public void setIdRemedio(int idRemedio) {
		this.idRemedio = idRemedio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDosagem() {
		return dosagem;
	}

	public void setDosagem(String dosagem) {
		this.dosagem = dosagem;
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

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public Remedio(int idRemedio, String nome, double preco, String dosagem, String tipo, boolean generico,
			int estoque) {
		super();
		this.idRemedio = idRemedio;
		this.nome = nome;
		this.preco = preco;
		this.dosagem = dosagem;
		this.tipo = tipo;
		this.generico = generico;
		this.estoque = estoque;
	}

	public Remedio() {
		super();
	}

}
