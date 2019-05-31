package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CadastroProduto extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][]"));

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "cell 0 0");

		textField = new JTextField();
		getContentPane().add(textField, "cell 0 1,growx");
		textField.setColumns(10);

		JLabel lblPreco = new JLabel("preco");
		getContentPane().add(lblPreco, "cell 0 2");

		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 0 3,growx");
		textField_1.setColumns(10);

		JLabel lblCategoria = new JLabel("categoria");
		getContentPane().add(lblCategoria, "cell 0 4,alignx left");

		JLabel lblEstoque = new JLabel("estoque");
		getContentPane().add(lblEstoque, "cell 1 4");

		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, "cell 0 5,growx");

		textField_2 = new JTextField();
		getContentPane().add(textField_2, "cell 1 5,growx");
		textField_2.setColumns(10);

		JLabel lblCodbarras = new JLabel("cod.barras");
		getContentPane().add(lblCodbarras, "cell 0 6");

		textField_3 = new JTextField();
		getContentPane().add(textField_3, "cell 0 7,growx");
		textField_3.setColumns(10);

		JButton btnSalvar = new JButton("salvar");
		getContentPane().add(btnSalvar, "cell 1 7");

	}

}
