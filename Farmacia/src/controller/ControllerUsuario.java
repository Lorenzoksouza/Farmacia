package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.UsuarioBO;
import model.seletor.UsuarioSeletor;
import model.vo.Nivel;
import model.vo.Usuario;

public class ControllerUsuario {

	UsuarioBO usuarioBO = new UsuarioBO();

	public String salvarUsuario(Usuario usuario) {
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

	public ArrayList<Nivel> consultarNiveis() {
		return usuarioBO.consultarNivel();
	}

	public List<Usuario> listarUsuarios(UsuarioSeletor seletor) {
		return usuarioBO.listarUsuarios(seletor);
	}
}
