package controller;

import model.bo.VendaBO;
import model.vo.Venda;

public class ControllerVenda {

	private VendaBO vendaBO = new VendaBO();

	public void cadastrarVendaController(Venda venda) {

		vendaBO.finalizarVendaBO(venda);

	}

}
