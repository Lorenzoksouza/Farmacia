package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CadastroUsuario extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtLogin;
	private JTextField txtSenha;
	private JTextField txtNivel;
	private JTextField txtAtivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroUsuario frame = new CadastroUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CadastroUsuario() {
		setTitle("Cadastro Usuário");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[][][grow]", "[][][][][][][][]"));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 1 1,alignx trailing");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 2 1,growx");
		txtNome.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		getContentPane().add(lblLogin, "cell 1 2,alignx trailing");

		txtLogin = new JTextField();
		getContentPane().add(txtLogin, "cell 2 2,growx");
		txtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		getContentPane().add(lblSenha, "cell 1 3,alignx trailing");

		txtSenha = new JTextField();
		getContentPane().add(txtSenha, "cell 2 3,growx");
		txtSenha.setColumns(10);

		JLabel lblNivel = new JLabel("Nível:");
		getContentPane().add(lblNivel, "cell 1 4,alignx trailing");

		txtNivel = new JTextField();
		getContentPane().add(txtNivel, "cell 2 4,growx");
		txtNivel.setColumns(10);

		JLabel lblAtivo = new JLabel("Ativo:");
		getContentPane().add(lblAtivo, "cell 1 5,alignx trailing");

		txtAtivo = new JTextField();
		getContentPane().add(txtAtivo, "cell 2 5,growx");
		txtAtivo.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		getContentPane().add(btnSalvar, "cell 2 7");

	}

}
