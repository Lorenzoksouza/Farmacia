package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class CadastroMedicamento extends JInternalFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMedicamento frame = new CadastroMedicamento();
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
	public CadastroMedicamento() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][]"));

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "cell 0 0");

		JLabel lblCodbarras = new JLabel("cod.barras");
		getContentPane().add(lblCodbarras, "cell 1 0");

		textField = new JTextField();
		getContentPane().add(textField, "cell 0 1,growx");
		textField.setColumns(10);

		textField_1 = new JTextField();
		getContentPane().add(textField_1, "cell 1 1,growx");
		textField_1.setColumns(10);

		JLabel lblPreco = new JLabel("preco");
		getContentPane().add(lblPreco, "cell 0 2");

		JLabel lblComposicao = new JLabel("composicao");
		getContentPane().add(lblComposicao, "cell 1 2");

		textField_2 = new JTextField();
		getContentPane().add(textField_2, "cell 0 3,growx");
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		getContentPane().add(textField_3, "cell 1 3,growx");
		textField_3.setColumns(10);

		JLabel lblDosagem = new JLabel("dosagem");
		getContentPane().add(lblDosagem, "cell 0 4");

		JLabel lblLaboratorio = new JLabel("laboratorio");
		getContentPane().add(lblLaboratorio, "cell 1 4");

		textField_4 = new JTextField();
		getContentPane().add(textField_4, "cell 0 5,growx");
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		getContentPane().add(textField_5, "cell 1 5,growx");
		textField_5.setColumns(10);

		JLabel lblTipo = new JLabel("tipo");
		getContentPane().add(lblTipo, "cell 0 6");

		JLabel lblEstoque = new JLabel("estoque");
		getContentPane().add(lblEstoque, "cell 1 6");

		JComboBox comboBox = new JComboBox();
		getContentPane().add(comboBox, "cell 0 7,growx");

		textField_6 = new JTextField();
		getContentPane().add(textField_6, "cell 1 7,growx");
		textField_6.setColumns(10);

		JCheckBox chckbxGenerico = new JCheckBox("generico");
		getContentPane().add(chckbxGenerico, "cell 0 8");

		JButton btnSalvar = new JButton("salvar");
		getContentPane().add(btnSalvar, "cell 1 8");

	}

}
