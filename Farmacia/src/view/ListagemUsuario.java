package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ListagemUsuario extends JInternalFrame {
	private JTextField txtNome;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemUsuario frame = new ListagemUsuario();
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
	public ListagemUsuario() {
		setTitle("Listagem usuário");
		setBounds(100, 100, 800, 619);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][grow]"));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 0,alignx left");

		table = new JTable();
		getContentPane().add(table, "cell 1 0 1 4,grow");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 1,alignx left");
		txtNome.setColumns(10);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setIcon(new ImageIcon(ListagemUsuario.class.getResource("/icons/search.png")));
		getContentPane().add(btnPesquisar, "cell 0 2,alignx left");

	}

}
