package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class Venda extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Venda frame = new Venda();
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
	public Venda() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[grow][][][][][][grow]"));

		JLabel lblCodbarra = new JLabel("cod.barra");
		getContentPane().add(lblCodbarra, "cell 0 0");

		table_1 = new JTable();
		table_1.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "nome", "data", "quantidade", "preco" }));
		getContentPane().add(table_1, "cell 1 0,grow");

		textField = new JTextField();
		getContentPane().add(textField, "cell 0 1,growx");
		textField.setColumns(10);

		JButton btnPesquisar = new JButton("pesquisar");
		getContentPane().add(btnPesquisar, "cell 0 2");

		JLabel lblListaDeProdutos = new JLabel("lista de produtos");
		getContentPane().add(lblListaDeProdutos, "cell 0 4");

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "flowx,cell 0 5");

		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 0 5");
		textField_1.setColumns(10);

		JButton btnPesquisar_1 = new JButton("pesquisar");
		getContentPane().add(btnPesquisar_1, "cell 0 5");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "cod.barras", "nome", "preco" }));
		getContentPane().add(table, "cell 0 6,grow");

	}

}
