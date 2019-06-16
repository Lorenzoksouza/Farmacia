package model.bo;

import java.util.Date;
import java.util.List;

import model.dao.ItemProdutoDAO;
import model.dao.ItemRemedioDAO;
import model.dao.VendaDAO;
import model.dto.VendaDTO;
import model.seletor.MercadoriaSeletor;
import model.vo.ItemProduto;
import model.vo.ItemRemedio;
import model.vo.Mercadoria;
import model.vo.Venda;

public class VendaBO {

	VendaDTO vendaDTO = new VendaDTO();
	VendaDAO vendaDAO = new VendaDAO();

	public List<Mercadoria> listarMercadoriasBO(MercadoriaSeletor seletor) {
		return vendaDTO.listarMercadoriasDTO();
	}

	public String salvar(double valorTotal, List<ItemProduto> itensProdutos, List<ItemRemedio> itensRemedios) {

		String mensagem = "";
		Venda novaVenda = vendaDAO.inserirVenda(valorTotal, new Date());

		if (novaVenda != null) {
			for (ItemProduto itemProduto : itensProdutos) {
				itemProduto.setVenda(novaVenda);
				ItemProdutoDAO itemProdutoDAO = new ItemProdutoDAO();
				itemProdutoDAO.inserirItemProduto(itemProduto);
			}
			for (ItemRemedio itemRemedio : itensRemedios) {
				itemRemedio.setVenda(novaVenda);
				ItemRemedioDAO itemRemedioDAO = new ItemRemedioDAO();
				itemRemedioDAO.inserirItemRemedio(itemRemedio);
			}
		}

		return mensagem;
	}

}
