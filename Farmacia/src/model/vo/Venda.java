package model.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Venda {

	private int idVenda;
	private ArrayList<Remedio> remedio;
	private Date dataVenda;

	public Venda(int idVenda, ArrayList<Remedio> remedio, Date dataVenda) {
		super();
		this.idVenda = idVenda;
		this.remedio = remedio;
		this.dataVenda = dataVenda;
	}

	public Venda() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(int idVenda) {
		this.idVenda = idVenda;
	}

	public ArrayList<Remedio> getRemedio() {
		return remedio;
	}

	public void setRemedio(ArrayList<Remedio> remedio) {
		this.remedio = remedio;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

}
