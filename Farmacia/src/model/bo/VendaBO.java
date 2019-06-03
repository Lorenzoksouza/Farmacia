package model.bo;

import model.dto.VendaDTO;
import model.vo.Venda;

public class VendaBO {

	VendaDTO vendaDAO = new VendaDTO();

	public void finalizarVendaBO(Venda venda) {

		vendaDAO.realizarVenda(venda);
	}

}
