package controller;

import java.util.List;

import model.bo.VendaBO;
import model.seletor.MercadoriaSeletor;
import model.vo.ItemProduto;
import model.vo.ItemRemedio;
import model.vo.Mercadoria;

public class ControllerVenda {

	private VendaBO vendaBO = new VendaBO();

	public List<Mercadoria> listarMercadorias(MercadoriaSeletor seletor) {
		return vendaBO.listarMercadoriasBO(seletor);
	}

	public String salvarVenda(double valorTotal, List<ItemProduto> itensProdutos, List<ItemRemedio> itensRemedios) {
		return vendaBO.salvar(valorTotal, itensProdutos, itensRemedios);
	}

}
