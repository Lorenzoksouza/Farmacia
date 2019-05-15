package model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Venda {

	private int idVenda;
	private ArrayList<ItemRemedio> itemRemedio;
	private ArrayList<ItemProduto> itemProduto;
	private Date dataVenda;

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public ArrayList<ItemRemedio> getItemRemedio() {
		return itemRemedio;
	}

	public void setItemRemedio(ArrayList<ItemRemedio> itemRemedio) {
		this.itemRemedio = itemRemedio;
	}

	public ArrayList<ItemProduto> getItemProduto() {
		return itemProduto;
	}

	public void setItemProduto(ArrayList<ItemProduto> itemProduto) {
		this.itemProduto = itemProduto;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Venda(int idVenda, ArrayList<ItemRemedio> itemRemedio, ArrayList<ItemProduto> itemProduto, Date dataVenda) {
		super();
		this.idVenda = idVenda;
		this.itemRemedio = itemRemedio;
		this.itemProduto = itemProduto;
		this.dataVenda = dataVenda;
	}

	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}
}