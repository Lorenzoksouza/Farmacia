package controller;

import java.util.List;

import model.bo.VendaBO;
import model.dto.VendaDTO;
import model.seletor.MercadoriaSeletor;
import model.vo.ItemProduto;
import model.vo.ItemRemedio;

public class ControllerVenda {

	private VendaBO vendaBO = new VendaBO();

	public List<VendaDTO> listarMercadorias(MercadoriaSeletor seletor) {
		return vendaBO.listarMercadoriasBO(seletor);
	}

	public String salvarVenda(double valorTotal, List<ItemProduto> itensProdutos, List<ItemRemedio> itensRemedios) {
		return vendaBO.salvar(valorTotal, itensProdutos, itensRemedios);
	}

}
