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
	private JTextField txtLaboratorio;
	private JTextField txtEstoque;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroMedicamento frame = new CadastroMedicamento(null);
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
	public CadastroMedicamento(Remedio remedio) {

		if (remedio == null) {
			System.out.println("remedio nulo");
		} else {
			System.out.println("dnlsadhhdsa");
		}
		setClosable(true);
//TODO em baixo
//		final Menu telaPai = (Menu) SwingUtilities.getWindowAncestor(this);
//		addInternalFrameListener(new InternalFrameAdapter() {
//			@Override
//			public void internalFrameClosing(InternalFrameEvent arg0) {
//				public void actionPerformed(ActionEvent evt) {
//					telaPai.chamarPai(CadastroMedicamento.class.getName());
//				}
//			}
//		});;

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

		JLabel lblLaboratorio = new JLabel("laboratorio");
		getContentPane().add(lblLaboratorio, "cell 1 4");

		txtDosagem = new JTextField();
		getContentPane().add(txtDosagem, "cell 0 5,growx");
		txtDosagem.setColumns(10);

		txtLaboratorio = new JTextField();
		getContentPane().add(txtLaboratorio, "cell 1 5,growx");
		txtLaboratorio.setColumns(10);

		JLabel lblTipo = new JLabel("tipo");
		getContentPane().add(lblTipo, "cell 0 6");

		JLabel lblEstoque = new JLabel("estoque");
		getContentPane().add(lblEstoque, "cell 1 6");

		JComboBox cmbTipo = new JComboBox();
		getContentPane().add(cmbTipo, "cell 0 7,growx");

		txtEstoque = new JTextField();
		getContentPane().add(txtEstoque, "cell 1 7,growx");
		txtEstoque.setColumns(10);

		JCheckBox chckbxGenerico = new JCheckBox("generico");
		getContentPane().add(chckbxGenerico, "cell 0 8");

		// preenche os txtbox com dados do remedio selecionado vindo da tela de listagem

		JButton btnSalvar = new JButton("salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Remedio remedio = new Remedio();
				remedio.setNome(txtNome.getText());
				remedio.setCodBarra(Integer.parseInt(txtCodBar.getText()));
				remedio.setPreco(Double.parseDouble(txtPreco.getText()));
				remedio.setComposicao(txtComposicao.getText());
				remedio.setDosagem(txtDosagem.getText());
				remedio.setLaboratorio(txtLaboratorio.getText());
				remedio.setEstoque(Integer.parseInt(txtEstoque.getText()));
				remedio.setTipo(cmbTipo.getSelectedItem().toString());
				remedio.setGenerico(chckbxGenerico.isSelected());

				ControllerRemedio controllerRemedio = new ControllerRemedio();
				controllerRemedio.salvar(remedio);
			}
		});
		getContentPane().add(btnSalvar, "cell 1 8");

	}

}
