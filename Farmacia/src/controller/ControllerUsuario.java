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

	public Usuario validarUsuario(String login, String senha) {
		return usuarioBO.validarUsuario(login, senha);

	}

	public Usuario consultarUsuario() {
		// TODO Auto-generated method stub
		return null;
	}

}
