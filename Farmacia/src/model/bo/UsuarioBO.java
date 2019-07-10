package model.bo;

import model.dao.UsuarioDAO;
import model.vo.Usuario;

public class UsuarioBO {

	UsuarioDAO usuarioDAO = new UsuarioDAO();

	public String salvarUsuario(Usuario usuario) {
		String mensagem = "";
		mensagem = usuarioDAO.salvarUsuario(usuario);

		return mensagem;
	}

	public boolean validarUsuario(String login, String senha) {
		return usuarioDAO.validarUsuario(login, senha);
	}

}
