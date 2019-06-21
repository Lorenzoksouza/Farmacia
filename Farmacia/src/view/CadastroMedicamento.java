package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import controller.ControllerRemedio;
import model.vo.FormaUso;
import model.vo.Laboratorio;
import model.vo.Remedio;
import net.miginfocom.swing.MigLayout;
import util.JNumberFormatField;

public class CadastroMedicamento extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtCodBar;
	private JTextField txtPreco;
	private JTextField txtComposicao;
	private JTextField txtDosagem;
	private JTextField txtEstoque;
	private JComboBox<Laboratorio> cmbLaboratorio;
	private JComboBox<FormaUso> cmbFormaUso;
	private JCheckBox chckbxGenerico;
	private ArrayList<Laboratorio> listaLaboratorios;
	private ArrayList<FormaUso> listaFormasUso;
	protected Scanner teclado = new Scanner(System.in);

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
		setFrameIcon(new ImageIcon(CadastroMedicamento.class.getResource("/icons/med3x.png")));
		getContentPane().setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setTitle("Cadastro de medicamentos");

		setClosable(true);

		setBounds(100, 100, 495, 285);
		getContentPane().setLayout(new MigLayout("", "[][][grow]", "[10px:n][][][][][][][][][]"));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 1");

		JLabel lblEspaco = new JLabel("    ");
		getContentPane().add(lblEspaco, "cell 1 1");

		JLabel lblCodbarras = new JLabel("C\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 2 1");

		MaskFormatter formatoNome = new MaskFormatter();

		try {
			formatoNome = new MaskFormatter("************************************************************");

		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoNome.setValidCharacters(
				"a·‡‚‰bcdeÈËÍÎfghiÌÏÓÔjklmnoÛÙˆpqrstu˙˘˚¸vwxyz-()/:A¡¿¬ƒBCDE…» ÀFGHIÕÃŒœJKLMNO”‘÷PQRSTU⁄Ÿ€‹VWXYZ ");

		JFormattedTextField txtNome = new JFormattedTextField(formatoNome);
		txtNome.setFocusLostBehavior(JFormattedTextField.PERSIST);
		txtNome.addFocusListener(new FocusListener() {

			public void focusGained(FocusEvent e) {
				txtNome.setText(txtNome.getText().trim());
			}

			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub

			}
		});

		getContentPane().add(txtNome, "cell 0 2,growx");
		txtNome.setColumns(10);

		MaskFormatter formatoCodBar = new MaskFormatter();

		try {
			formatoCodBar = new MaskFormatter("#############");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		formatoCodBar.setValidCharacters("0123456789");

		JFormattedTextField txtCodBar = new JFormattedTextField(formatoCodBar);

		getContentPane().add(txtCodBar, "cell 2 2,growx");
		txtCodBar.setColumns(10);

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		getContentPane().add(lblPreco, "cell 0 3");

		JLabel lblComposicao = new JLabel("Composi\u00E7\u00E3o:");
		getContentPane().add(lblComposicao, "cell 2 3");

		JNumberFormatField txtPreco = new JNumberFormatField(2);
		// txtPreco = new JFormattedTextField();
		getContentPane().add(txtPreco, "cell 0 4,growx");
		txtPreco.setColumns(10);

		txtComposicao = new JFormattedTextField();

		MaskFormatter formatoComposicao = new MaskFormatter();

		try {
			formatoComposicao = new MaskFormatter(
					"**************************************************************************************************");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoComposicao.setValidCharacters(
				"a·‡‚‰bcdeÈËÍÎfghiÌÏÓÔjklmnoÛÙˆpqrstu˙˘˚¸vwxyz-()/:A¡¿¬ƒBCDE…» ÀFGHIÕÃŒœJKLMNO”‘÷PQRSTU⁄Ÿ€‹VWXYZ ");

		formatoComposicao.install((JFormattedTextField) txtComposicao);

		getContentPane().add(txtComposicao, "cell 2 4,growx");
		txtComposicao.setColumns(10);

		JLabel lblDosagem = new JLabel("Dosagem:");
		getContentPane().add(lblDosagem, "cell 0 5");

		txtDosagem = new JFormattedTextField();

		MaskFormatter formatoDosagem = new MaskFormatter();

		try {
			formatoDosagem = new MaskFormatter("#####");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoDosagem.setValidCharacters("0123456789");

		formatoDosagem.install((JFormattedTextField) txtDosagem);

		getContentPane().add(txtDosagem, "cell 0 6,growx");
		txtDosagem.setColumns(10);

		consultarLaboratorio();

		JLabel lblLaboratorio = new JLabel("Laborat\u00F3rio:");
		getContentPane().add(lblLaboratorio, "cell 2 5");

		cmbLaboratorio = new JComboBox();
		cmbLaboratorio.setBackground(Color.WHITE);
		cmbLaboratorio.setModel(new DefaultComboBoxModel(listaLaboratorios.toArray()));
		cmbLaboratorio.setSelectedIndex(-1);
		getContentPane().add(cmbLaboratorio, "cell 2 6,growx");

		consultarFormaUso();

		JLabel lblFormaUso = new JLabel("Forma de uso:");
		getContentPane().add(lblFormaUso, "flowx,cell 0 7,alignx left");

		cmbFormaUso = new JComboBox();
		cmbFormaUso.setBackground(Color.WHITE);
		cmbFormaUso.setModel(new DefaultComboBoxModel(listaFormasUso.toArray()));
		cmbFormaUso.setSelectedIndex(-1);
		getContentPane().add(cmbFormaUso, "flowx,cell 0 8,growx");

		JLabel labelEspaco2 = new JLabel(" ");
		getContentPane().add(labelEspaco2, "cell 0 8");

		txtEstoque = new JFormattedTextField();

		MaskFormatter formatoEstoque = new MaskFormatter();

		try {
			formatoEstoque = new MaskFormatter("###");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoEstoque.setValidCharacters("0123456789");

		formatoEstoque.install((JFormattedTextField) txtEstoque);

		getContentPane().add(txtEstoque, "cell 0 8,growx");
		txtEstoque.setColumns(10);

		JLabel lblEstoque = new JLabel("    Estoque:");
		getContentPane().add(lblEstoque, "cell 0 7,alignx right");

		chckbxGenerico = new JCheckBox("Gen\u00E9rico");
		chckbxGenerico.setBackground(Color.WHITE);
		getContentPane().add(chckbxGenerico, "cell 0 8");

		// listagem

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setOpaque(true);
		btnSalvar.setPreferredSize(new Dimension(80, 30));
		btnSalvar.setBorder(new LineBorder(Color.gray, 2, true));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Remedio remedio = new Remedio();
				remedio.setNome(txtNome.getText());
				remedio.setCodBarra(txtCodBar.getText());
				remedio.setPreco(Double.parseDouble(txtPreco.getText()));
				remedio.setComposicao(txtComposicao.getText());
				remedio.setDosagem(txtDosagem.getText());

				Laboratorio lab = new Laboratorio();
				lab.setIdLaboratorio(listaLaboratorios.get(cmbLaboratorio.getSelectedIndex()).getIdLaboratorio());
				lab.setNomeLaboratorio(listaLaboratorios.get(cmbLaboratorio.getSelectedIndex()).getNomeLaboratorio());
				remedio.setLaboratorio(lab);

				FormaUso formaUso = new FormaUso();
				formaUso.setIdFormaUso(listaFormasUso.get(cmbLaboratorio.getSelectedIndex()).getIdFormaUso());
				formaUso.setDescricao(listaFormasUso.get(cmbLaboratorio.getSelectedIndex()).getDescricao());
				remedio.setFormaUso(formaUso);

				remedio.setEstoque(Integer.parseInt(txtEstoque.getText()));
				remedio.getFormaUso().setDescricao(cmbFormaUso.getSelectedItem().toString());
				remedio.setGenerico(chckbxGenerico.isSelected());

				ControllerRemedio controllerRemedio = new ControllerRemedio();
				String mensagem = "";
				mensagem = controllerRemedio.salvar(remedio);
				JOptionPane.showMessageDialog(null, mensagem);

				limparCampos();
			}
		});
		getContentPane().add(btnSalvar, "cell 2 9,alignx right");
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

	private void consultarLaboratorio() {
		ControllerRemedio controllerRemedio = new ControllerRemedio();
		listaLaboratorios = controllerRemedio.consultarLaboratorio();
	}

	private void consultarFormaUso() {
		ControllerRemedio controllerRemedio = new ControllerRemedio();
		listaFormasUso = controllerRemedio.consultarFormaUso();
	}

	public void limparCampos() {
		txtNome.setText("");
		txtCodBar.setText("");
		txtPreco.setText("");
		txtComposicao.setText("");
		txtDosagem.setText("");
		txtEstoque.setText("");
		cmbLaboratorio.setSelectedIndex(-1);
		cmbFormaUso.setSelectedIndex(-1);
		chckbxGenerico.setSelected(isClosed);
	}

}
