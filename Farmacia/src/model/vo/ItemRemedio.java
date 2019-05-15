package model.vo;

public class ItemRemedio {

	private int idItemRemedio;
	private int idRemedio;
	private int idVenda;
	private int quantidade;

	public int getIdItemRemedio() {
		return idItemRemedio;
	}

	public void setIdItemRemedio(int idItemRemedio) {
		this.idItemRemedio = idItemRemedio;
	}

	public int getIdRemedio() {
		return idRemedio;
	}

	public void setIdRemedio(int idRemedio) {
		this.idRemedio = idRemedio;
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public ItemRemedio(int idItemRemedio, int idRemedio, int idVenda, int quantidade) {
		super();
		this.idItemRemedio = idItemRemedio;
		this.idRemedio = idRemedio;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
	}

	public ItemRemedio() {
		super();
		// TODO Auto-generated constructor stub
	}
}
