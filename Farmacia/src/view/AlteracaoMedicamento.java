package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.ControllerRemedio;
import model.vo.FormaUso;
import model.vo.Laboratorio;
import model.vo.Remedio;
import net.miginfocom.swing.MigLayout;
import util.JNumberFormatField;
import util.JTextFieldLimit;

public class AlteracaoMedicamento extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtCodBar;
	private JNumberFormatField txtPreco;
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
					Remedio r = new Remedio();
					AlteracaoMedicamento frame = new AlteracaoMedicamento(r);
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
	public AlteracaoMedicamento(Remedio remedioSelecionado) {
		setFrameIcon(new ImageIcon(AlteracaoMedicamento.class.getResource("/icons/med3x.png")));
		getContentPane().setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setTitle("Alteração de medicamentos");

		setClosable(true);

		setBounds(100, 100, 420, 315);
		getContentPane().setLayout(new MigLayout("", "[][][grow]", "[10px:n][][][][][][][][][][][][]"));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 1");

		JLabel lblEspaco = new JLabel("    ");
		getContentPane().add(lblEspaco, "cell 1 1");

		JLabel lblCodbarras = new JLabel("C\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 2 1");

		// Nome

		txtNome = new JTextField();
		txtNome.setEnabled(false);
		txtNome.setEditable(false);

		txtNome.setDocument(new JTextFieldLimit(150));

		getContentPane().add(txtNome, "cell 0 2,growx");
		txtNome.setColumns(10);

		// Código de barra

		JLabel lblValidacaoTxtCodBar = new JLabel(" ");
		lblValidacaoTxtCodBar.setForeground(Color.RED);

		txtCodBar = new JTextField();
		txtCodBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
					arg0.consume();
			}
		});

		txtCodBar.setDocument(new JTextFieldLimit(13));

		getContentPane().add(txtCodBar, "cell 2 2,growx");
		txtCodBar.setColumns(10);

		getContentPane().add(lblValidacaoTxtCodBar, "cell 2 3,alignx left");

		JLabel lblDosagem = new JLabel("Dosagem:");
		getContentPane().add(lblDosagem, "cell 0 4");

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		getContentPane().add(lblPreco, "cell 2 4");

		// Dosagem
		txtDosagem = new JTextField();

		txtDosagem.setDocument(new JTextFieldLimit(15));

		getContentPane().add(txtDosagem, "cell 0 5,alignx left");
		txtDosagem.setColumns(10);

		// Preço

		txtPreco = new JNumberFormatField(2);
		getContentPane().add(txtPreco, "cell 2 5,growx");
		txtPreco.setColumns(10);

		JLabel lblValidacaoTxtDosagem = new JLabel(" ");
		lblValidacaoTxtDosagem.setForeground(Color.RED);
		getContentPane().add(lblValidacaoTxtDosagem, "cell 2 6");

		// Laboratório

		consultarLaboratorio();

		JLabel lblComposicao = new JLabel("Composi\u00E7\u00E3o:");
		getContentPane().add(lblComposicao, "cell 0 7");

		JLabel lblLaboratorio = new JLabel("Laborat\u00F3rio:");
		getContentPane().add(lblLaboratorio, "cell 2 7");

		// Composição

		txtComposicao = new JTextField();

		txtComposicao.setDocument(new JTextFieldLimit(100));

		getContentPane().add(txtComposicao, "cell 0 8,growx");
		txtComposicao.setColumns(10);

		cmbLaboratorio = new JComboBox();
		cmbLaboratorio.setBackground(Color.WHITE);
		cmbLaboratorio.setModel(new DefaultComboBoxModel(listaLaboratorios.toArray()));
		cmbLaboratorio.setSelectedIndex(-1);
		getContentPane().add(cmbLaboratorio, "cell 2 8,growx");

		// Forma de uso

		consultarFormaUso();

		JLabel lblEspaco4 = new JLabel(" ");
		lblEspaco4.setForeground(Color.RED);
		getContentPane().add(lblEspaco4, "cell 2 9");

		JLabel lblFormaUso = new JLabel("Forma de uso:");
		getContentPane().add(lblFormaUso, "flowx,cell 0 10,alignx left");

		cmbFormaUso = new JComboBox();
		cmbFormaUso.setBackground(Color.WHITE);
		cmbFormaUso.setModel(new DefaultComboBoxModel(listaFormasUso.toArray()));
		cmbFormaUso.setSelectedIndex(-1);
		getContentPane().add(cmbFormaUso, "flowx,cell 0 11,growx");

		JLabel labelEspaco2 = new JLabel("    ");
		getContentPane().add(labelEspaco2, "cell 0 11");

		// Estoque

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
					arg0.consume();
			}
		});

		getContentPane().add(txtEstoque, "cell 0 11,growx");
		txtEstoque.setColumns(10);

		JLabel lblEstoque = new JLabel("    Estoque:");
		getContentPane().add(lblEstoque, "cell 0 10,alignx right");

		// Genérico

		chckbxGenerico = new JCheckBox("Gen\u00E9rico");
		chckbxGenerico.setBackground(Color.WHITE);
		getContentPane().add(chckbxGenerico, "cell 0 11");

		// listagem

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(AlteracaoMedicamento.class.getResource("/icons/check.png")));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setOpaque(true);
		btnSalvar.setPreferredSize(new Dimension(80, 30));
		btnSalvar.setBorder(new LineBorder(Color.gray, 2, true));
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// try & catch apenas para teste, pode mexer neles de boa
				try {
					Remedio remedio = new Remedio();
					remedio.setNome(txtNome.getText());
					remedio.setCodBarra(txtCodBar.getText());
					remedio.setPreco(Double.parseDouble(txtPreco.getText().replace(",", ".")));
					remedio.setComposicao(txtComposicao.getText());
					remedio.setDosagem(txtDosagem.getText());

					Laboratorio lab = new Laboratorio();
					lab.setIdLaboratorio(listaLaboratorios.get(cmbLaboratorio.getSelectedIndex()).getIdLaboratorio());
					lab.setNomeLaboratorio(
							listaLaboratorios.get(cmbLaboratorio.getSelectedIndex()).getNomeLaboratorio());
					remedio.setLaboratorio(lab);

					FormaUso formaUso = new FormaUso();
					formaUso.setIdFormaUso(listaFormasUso.get(cmbLaboratorio.getSelectedIndex()).getIdFormaUso());
					formaUso.setDescricao(listaFormasUso.get(cmbLaboratorio.getSelectedIndex()).getDescricao());
					remedio.setFormaUso(formaUso);
					try {
						remedio.setEstoque(Integer.parseInt(txtEstoque.getText().trim()));
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Verificar se o estoque foi preenchido");
					}
					try {
						remedio.getFormaUso().setDescricao(cmbFormaUso.getSelectedItem().toString());
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Verificar se a forma de uso foi preenchida");
					}
					remedio.setGenerico(chckbxGenerico.isSelected());

					ControllerRemedio controllerRemedio = new ControllerRemedio();
					String mensagem = "";
					mensagem = controllerRemedio.atualizar(remedio);

					limparCampos();
					JOptionPane.showMessageDialog(null, mensagem);
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Verificar se todas as caixas foram preenchidas");
				}
			}
		});
		getContentPane().add(btnSalvar, "cell 2 11,alignx right");
	}

	public void preencherCampos(Remedio remedio) {

		txtCodBar.setText(remedio.getCodBarra());
		txtNome.setText(remedio.getNome());
		txtDosagem.setText(remedio.getDosagem());
		txtPreco.setText(remedio.getPreco() + "");
		txtComposicao.setText(remedio.getComposicao());
		cmbLaboratorio.setSelectedItem(remedio.getLaboratorio().getNomeLaboratorio());
		cmbFormaUso.setSelectedItem(remedio.getFormaUso().getDescricao());
		txtEstoque.setText(remedio.getEstoque() + "");
		chckbxGenerico.setSelected(remedio.isGenerico());
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
