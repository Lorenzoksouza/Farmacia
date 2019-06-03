package model.bo;

import java.util.List;

import model.dto.VendaDTO;
import model.seletor.MercadoriaSeletor;
import model.vo.Mercadoria;
import model.vo.Venda;

public class VendaBO {

	VendaDTO vendaDTO = new VendaDTO();

	public void finalizarVendaBO(Venda venda) {

		vendaDTO.realizarVenda(venda);
	}

	public List<Mercadoria> listarMercadoriasBO(MercadoriaSeletor seletor) {
		return vendaDTO.listarMercadoriasDTO();
	}

}
