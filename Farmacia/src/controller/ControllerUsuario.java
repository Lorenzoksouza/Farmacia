package controller;

import model.bo.UsuarioBO;
import model.vo.Usuario;

public class ControllerUsuario {

	UsuarioBO usuarioBO = new UsuarioBO();

	private String salvarUsuario(Usuario usuario) {
		String mensagem = "";
		mensagem = usuarioBO.salvarUsuario(usuario);
		return mensagem;
	}

	private String modificarStatus(Usuario usuario) {
		String mensagem = "";
		mensagem = usuarioBO.modificarStatus(usuario);
		return mensagem;
	}

	public boolean validarUsuario() {
		if (usuarioBO.validarUsuario()) {
			return true;
		} else {
			return false;
		}
	}

}
