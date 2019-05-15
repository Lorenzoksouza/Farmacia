package model.vo;

public class ItemProduto {

	private int idItemProduto;
	private int idProduto;
	private int idVenda;
	private int quantidade;

	public int getIdItemProduto() {
		return idItemProduto;
	}

	public void setIdItemProduto(int idItemProduto) {
		this.idItemProduto = idItemProduto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
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

	public ItemProduto(int idItemProduto, int idProduto, int idVenda, int quantidade) {
		super();
		this.idItemProduto = idItemProduto;
		this.idProduto = idProduto;
		this.idVenda = idVenda;
		this.quantidade = quantidade;
	}

	public ItemProduto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
