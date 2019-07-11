package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ControllerUsuario;
import model.vo.Usuario;
import net.miginfocom.swing.MigLayout;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/icons/logo.png")));
		setTitle("Login");
		setBackground(Color.WHITE);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][grow][][][grow]", "[][][][][][][]"));

		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 21));
		contentPane.add(lblLogin, "cell 2 1");

		txtLogin = new JTextField();
		txtLogin.setFont(new Font("Tahoma", Font.PLAIN, 21));
		contentPane.add(txtLogin, "cell 4 1,growx");
		txtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 21));
		contentPane.add(lblSenha, "cell 2 3");

		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 21));
		contentPane.add(txtSenha, "cell 4 3,growx");

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ControllerUsuario usuarioController = new ControllerUsuario();
				Usuario usuario = usuarioController.validarUsuario(txtLogin.getText(), txtSenha.getText());
				if (usuario != null) {
					Menu menu = new Menu(usuario);
					menu.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Usuario invalido, tente novamente");
				}
			}
		});
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnEntrar, "cell 4 5");
	}

}
