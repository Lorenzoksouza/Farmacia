package model.bo;

import model.dao.VendaDAO;
import model.vo.Venda;

public class VendaBO {

	VendaDAO vendaDAO = new VendaDAO();

	public void cadastrarVendaBO(Venda venda) {

		vendaDAO.cadastrarVenda(venda);
	}

}
