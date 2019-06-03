package controller;

import java.util.List;

import model.bo.VendaBO;
import model.seletor.MercadoriaSeletor;
import model.vo.Mercadoria;
import model.vo.Venda;

public class ControllerVenda {

	private VendaBO vendaBO = new VendaBO();

	public void cadastrarVendaController(Venda venda) {

		vendaBO.finalizarVendaBO(venda);

	}

	public List<Mercadoria> listarMercadorias(MercadoriaSeletor seletor) {
		return vendaBO.listarMercadoriasBO(seletor);
	}

}
