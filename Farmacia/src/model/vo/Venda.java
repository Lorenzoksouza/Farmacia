package model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Venda {

	private int idVenda;
	private Double valor;
	private ArrayList<ItemRemedio> itensRemedios;
	private ArrayList<ItemProduto> itensProdutos;
	private Date dataVenda;

	public Venda(int idVenda, Double valor, ArrayList<ItemRemedio> itemRemedio, ArrayList<ItemProduto> itemProduto,
			Date dataVenda) {
		super();
		this.idVenda = idVenda;
		this.valor = valor;
		this.itensRemedios = itemRemedio;
		this.itensProdutos = itemProduto;
		this.dataVenda = dataVenda;
	}

	public Venda() {
		super();
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public ArrayList<ItemRemedio> getItemRemedio() {
		return itensRemedios;
	}

	public void setItemRemedio(ArrayList<ItemRemedio> itemRemedio) {
		this.itensRemedios = itemRemedio;
	}

	public ArrayList<ItemProduto> getItemProduto() {
		return itensProdutos;
	}

	public void setItemProduto(ArrayList<ItemProduto> itemProduto) {
		this.itensProdutos = itemProduto;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

}