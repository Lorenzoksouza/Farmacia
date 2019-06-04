package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ControllerRemedio;
import model.vo.Remedio;
import net.miginfocom.swing.MigLayout;

public class CadastroMedicamento extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtCodBar;
	private JTextField txtPreco;
	private JTextField txtComposicao;
	private JTextField txtDosagem;
	private JTextField txtEstoque;
	private JComboBox<String> cmbLaboratorio;
	private JComboBox<String> cmbFormaUso;

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
	 * Create the frame. INSERT
	 */
	public CadastroMedicamento() {
		setTitle("Cadastro de Medicamentos");

		setClosable(true);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][]"));

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "cell 0 0");

		JLabel lblCodbarras = new JLabel("cod.barras");
		getContentPane().add(lblCodbarras, "cell 1 0");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 1,growx");
		txtNome.setColumns(10);

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 1 1,growx");
		txtCodBar.setColumns(10);

		JLabel lblPreco = new JLabel("preco");
		getContentPane().add(lblPreco, "cell 0 2");

		JLabel lblComposicao = new JLabel("composicao");
		getContentPane().add(lblComposicao, "cell 1 2");

		txtPreco = new JTextField();
		getContentPane().add(txtPreco, "cell 0 3,growx");
		txtPreco.setColumns(10);

		txtComposicao = new JTextField();
		getContentPane().add(txtComposicao, "cell 1 3,growx");
		txtComposicao.setColumns(10);

		JLabel lblDosagem = new JLabel("dosagem");
		getContentPane().add(lblDosagem, "cell 0 4");

		txtDosagem = new JTextField();
		getContentPane().add(txtDosagem, "cell 0 5,growx");
		txtDosagem.setColumns(10);

		consultarLaboratorio();

		JLabel lblLaboratorio = new JLabel("Laborat�rio");
		getContentPane().add(lblLaboratorio, "cell 1 4");

		cmbLaboratorio = new JComboBox<String>();
//		cmbLaboratorio.setModel(new DefaultComboBoxModel<String>(listaLaboratorios.toArray()));
		getContentPane().add(cmbLaboratorio, "cell 1 5,growx");

		consultarFormaUso();

		JLabel lblFormaUso = new JLabel("Forma de Uso");
		getContentPane().add(lblFormaUso, "cell 0 6");

		cmbFormaUso = new JComboBox<String>();
//		cmbFormaUso.setModel(new DefaultComboBoxModel<String>(listaFormaUso.toArray()));
		getContentPane().add(cmbFormaUso, "cell 0 7,growx");

		JLabel lblEstoque = new JLabel("estoque");
		getContentPane().add(lblEstoque, "cell 1 6");

		txtEstoque = new JTextField();
		getContentPane().add(txtEstoque, "cell 1 7,growx");
		txtEstoque.setColumns(10);

		JCheckBox chckbxGenerico = new JCheckBox("generico");
		getContentPane().add(chckbxGenerico, "cell 0 8");

		// listagem

		JButton btnSalvar = new JButton("salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Remedio remedio = new Remedio();
				remedio.setNome(txtNome.getText());
				remedio.setCodBarra(txtCodBar.getText());
				remedio.setPreco(Double.parseDouble(txtPreco.getText()));
				remedio.setComposicao(txtComposicao.getText());
				remedio.setDosagem(txtDosagem.getText());
				remedio.setLaboratorio(cmbLaboratorio.getSelectedItem().toString());
				remedio.setEstoque(Integer.parseInt(txtEstoque.getText()));
				remedio.setFormaUso(cmbFormaUso.getSelectedItem().toString());
				remedio.setGenerico(chckbxGenerico.isSelected());

				ControllerRemedio controllerRemedio = new ControllerRemedio();
				controllerRemedio.salvar(remedio);
			}
		});
		getContentPane().add(btnSalvar, "cell 1 8");
	}

	public CadastroMedicamento(Remedio remedio) {
		new CadastroMedicamento();
		txtNome.setText(remedio.getNome());
		txtCodBar.setText(remedio.getCodBarra() + "");
		txtPreco.setText(remedio.getPreco() + "");
		txtComposicao.setText(remedio.getComposicao());
		txtDosagem.setText(remedio.getDosagem());
		txtEstoque.setText(remedio.getEstoque() + "");
		cmbLaboratorio.setSelectedItem(remedio.getLaboratorio());
		cmbFormaUso.setSelectedItem(remedio.getFormaUso());
	}

	// Criar os m�todos de consultar Lista de Forma de Uso e Laborat�rio, no DAO j�
	// foi criado!!
	private void consultarLaboratorio() {
		// TODO Auto-generated method stub
		// fazer retornar listaLaboratorios do RemedioDAO!
		// ap�s isto, descomentar linha 96
	}

	private void consultarFormaUso() {
		// TODO Auto-generated method stub
		// fazer retornar listaFormaUso do RemedioDAO!
		// ap�s isto, descomentar linha 105
	}
}
