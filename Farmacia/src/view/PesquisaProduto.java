package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class PesquisaProduto extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PesquisaProduto frame = new PesquisaProduto();
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
	public PesquisaProduto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[grow][][][][][][][][]"));

		JLabel lblCodbarras = new JLabel("cod.barras");
		getContentPane().add(lblCodbarras, "cell 0 0");

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "cod.barra", "nome", "categoria" }));
		getContentPane().add(table, "cell 1 0,grow");

		textField = new JTextField();
		getContentPane().add(textField, "cell 0 1,growx");
		textField.setColumns(10);

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "cell 0 2");

		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 0 3,growx");
		textField_1.setColumns(10);

		JLabel lblCategoria = new JLabel("categoria");
		getContentPane().add(lblCategoria, "cell 0 4");

		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, "cell 0 5,growx");

		JButton btnPesquisar = new JButton("pesquisar");
		getContentPane().add(btnPesquisar, "cell 0 6");

		JButton btnExcluir = new JButton("excluir");
		getContentPane().add(btnExcluir, "flowx,cell 0 7");

		JButton btnAlterar = new JButton("alterar");
		getContentPane().add(btnAlterar, "cell 0 7");

		JButton btnRelatorio = new JButton("relatorio");
		getContentPane().add(btnRelatorio, "cell 0 8");

	}

}
