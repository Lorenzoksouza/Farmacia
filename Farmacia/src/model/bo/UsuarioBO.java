package model.bo;

import model.dao.UsuarioDAO;
import model.vo.Usuario;

public class UsuarioBO {

	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public String salvarUsuario(Usuario usuario) {
		String mensagem = "";

		return usuarioDAO.salvarUsuario(usuario);
	}

	public String modificarStatus(Usuario usuario) {
		String mensagem = "";

		return usuarioDAO.modificarStatus(usuario);
	}

}
