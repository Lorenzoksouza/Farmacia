package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import controller.ControllerRemedio;
import model.seletor.RemedioSeletor;
import model.vo.FormaUso;
import model.vo.Remedio;
import net.miginfocom.swing.MigLayout;

public class ListagemMedicamento extends JInternalFrame {
	CadastroMedicamento cadastroMedicamento = null;

	private JTextField txtCodBar = null;
	private JTextField txtNome;
	private JTextField txtComposicao;
	private JTable tblRemedios;
	private JComboBox cmbFormaUso;
	private JCheckBox chckbxGenerico;
	private JButton btnGerarXls;

	private List<Remedio> remediosConsultados;
	private int totalPaginas = 1;
	private int paginaAtual = 1;

	private ArrayList<FormaUso> listaFormasUso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemMedicamento frame = new ListagemMedicamento();
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
	public ListagemMedicamento() {
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setFrameIcon(new ImageIcon(ListagemMedicamento.class.getResource("/icons/med3x.png")));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Pesquisa de remedios");
		setClosable(true);
		setBounds(100, 100, 680, 540);
		getContentPane()
				.setLayout(new MigLayout("", "[211.00,grow][][grow]", "[][][][][][][][][][][10px:n][][][grow]"));

		JLabel lblCodbarras = new JLabel("C\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 0 0");

		JLabel lblespaco2 = new JLabel("      ");
		getContentPane().add(lblespaco2, "cell 1 0");

		tblRemedios = new JTable();
		tblRemedios.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		tblRemedios.setColumnSelectionAllowed(true);
		tblRemedios.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo de Barras", "Dosagem", "Composi\u00E7\u00E3o", "Generico", "Nome",
						"Data Cad.", "Pre\u00E7o", "Estoque", "Forma Uso", "Laboratorio" }));
		tblRemedios.getColumnModel().getColumn(0).setPreferredWidth(94);
		tblRemedios.getColumnModel().getColumn(1).setPreferredWidth(58);
		tblRemedios.getColumnModel().getColumn(2).setPreferredWidth(69);
		tblRemedios.getColumnModel().getColumn(3).setPreferredWidth(56);
		tblRemedios.getColumnModel().getColumn(4).setPreferredWidth(88);
		tblRemedios.getColumnModel().getColumn(5).setPreferredWidth(61);
		tblRemedios.getColumnModel().getColumn(6).setPreferredWidth(40);
		tblRemedios.getColumnModel().getColumn(7).setPreferredWidth(50);
		tblRemedios.getColumnModel().getColumn(8).setPreferredWidth(62);
		tblRemedios.getColumnModel().getColumn(9).setPreferredWidth(68);
		getContentPane().add(tblRemedios, "cell 2 0 1 14,grow");

		txtCodBar = new JFormattedTextField();

		MaskFormatter formatoCodBar = new MaskFormatter();

		try {
			formatoCodBar = new MaskFormatter("################");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoCodBar.setValidCharacters("0123456789");

		formatoCodBar.install((JFormattedTextField) txtCodBar);

		getContentPane().add(txtCodBar, "cell 0 1,growx,aligny center");
		txtCodBar.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 2");

		txtNome = new JFormattedTextField();

		MaskFormatter formatonome = new MaskFormatter();

		try {
			formatonome = new MaskFormatter("************************************************************");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatonome.setValidCharacters(
				"aáàâäbcdeéèêëfghiíìîïjklmnoóôöpqrstuúùûüvwxyz-()/:AÁÀÂÄBCDEÉÈÊËFGHIÍÌÎÏJKLMNOÓÔÖPQRSTUÚÙÛÜVWXYZ");

		formatonome.install((JFormattedTextField) txtNome);

		getContentPane().add(txtNome, "cell 0 3,growx");
		txtNome.setColumns(10);

		JLabel lblComposicao = new JLabel("Composi\u00E7\u00E3o:");
		getContentPane().add(lblComposicao, "cell 0 4");

		txtComposicao = new JFormattedTextField();

		MaskFormatter formatoComposicao = new MaskFormatter();

		try {
			formatoComposicao = new MaskFormatter(
					"************************************************************************************************************************");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoComposicao.setValidCharacters(
				"aáàâäbcdeéèêëfghiíìîïjklmnoóôöpqrstuúùûüvwxyz-()/:AÁÀÂÄBCDEÉÈÊËFGHIÍÌÎÏJKLMNOÓÔÖPQRSTUÚÙÛÜVWXYZ");

		formatoComposicao.install((JFormattedTextField) txtComposicao);

		getContentPane().add(txtComposicao, "cell 0 5,growx");
		txtComposicao.setColumns(10);

		JLabel lblFormaUso = new JLabel("Forma de uso:");
		getContentPane().add(lblFormaUso, "cell 0 6");

		this.consultarFormaUso();

		cmbFormaUso = new JComboBox(listaFormasUso.toArray());
		cmbFormaUso.setBackground(Color.WHITE);
		getContentPane().add(cmbFormaUso, "cell 0 7,growx");
		cmbFormaUso.setSelectedIndex(-1);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setPreferredSize(new Dimension(80, 30));
		btnPesquisar.setBorder(new LineBorder(Color.gray, 2, true));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarMedicamentos();
			}
		});

		JCheckBox chckbxGenerico = new JCheckBox("Gen\u00E9rico");
		chckbxGenerico.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxGenerico.setBackground(Color.WHITE);
		getContentPane().add(chckbxGenerico, "flowx,cell 0 8,alignx center");
		getContentPane().add(btnPesquisar, "cell 0 9,growx");

		btnGerarXls = new JButton("Relatorio");
		btnGerarXls.setPreferredSize(new Dimension(100, 30));
		btnGerarXls.setBorder(new LineBorder(Color.gray, 2, true));
		btnGerarXls.setBackground(Color.WHITE);
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setPreferredSize(new Dimension(30, 30));
		btnAlterar.setBorder(new LineBorder(Color.gray, 2, true));
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Remedio remedioSelecionado = new Remedio();

//				remedioSelecionado = remediosConsultados.get(tblRemedios.getSelectedRow() - 1);

				remedioSelecionado.setNome(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getNome());
				remedioSelecionado.setCodBarra(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getCodBarra());
				remedioSelecionado.setPreco(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getPreco());
				remedioSelecionado
						.setComposicao(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getComposicao());
				remedioSelecionado.setDosagem(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getDosagem());
				remedioSelecionado
						.setLaboratorio(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getLaboratorio());
				remedioSelecionado.setEstoque(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getEstoque());
				remedioSelecionado.setFormaUso(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).getFormaUso());
				remedioSelecionado.setGenerico(remediosConsultados.get(tblRemedios.getSelectedRow() - 1).isGenerico());

				cadastroMedicamento = new CadastroMedicamento(remedioSelecionado);
				cadastroMedicamento.setVisible(true);

			}
		});

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setPreferredSize(new Dimension(30, 30));
		btnExcluir.setBorder(new LineBorder(Color.gray, 2, true));
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mensagem = "";
				String remedioSelecionado = (String) tblRemedios.getValueAt(tblRemedios.getSelectedRow(), 1);

				ControllerRemedio controllerRemedio = new ControllerRemedio();

				if (controllerRemedio.existeRemedioPorCodBar(remedioSelecionado)) {
					mensagem = "Remedio não foi cadastrado";
				} else {
					mensagem = controllerRemedio.excluir(remedioSelecionado);
				}
			}
		});

		// espaco entre os botoes (pesquisa e excluir)
		JLabel lblespaco = new JLabel("    ");
		lblespaco.setEnabled(false);
		getContentPane().add(lblespaco, "cell 0 10");
		getContentPane().add(btnExcluir, "flowx,cell 0 11,growx");
		getContentPane().add(btnAlterar, "cell 0 11,growx");
		getContentPane().add(btnGerarXls, "cell 0 12,alignx center");

		JLabel lblespaco3 = new JLabel("                        ");
		getContentPane().add(lblespaco3, "cell 0 8");

	}

	private void pesquisarMedicamentos() {
		// lblPaginaAtual.setText(paginaAtual + "");

		ControllerRemedio controlador = new ControllerRemedio();
		RemedioSeletor seletor = new RemedioSeletor();

		List<Remedio> remedios = controlador.listarRemedios(seletor);

		seletor.setLimite(10);

		int quociente = remedios.size() / seletor.getLimite();
		int resto = remedios.size() % seletor.getLimite();

		if (resto == 0) {
			totalPaginas = quociente;
		} else {
			totalPaginas = quociente + 1;
		}
		// lblTotalPaginas.setText(totalPaginas + "");

		seletor.setPagina(paginaAtual);

		// Preenche os campos de filtro da tela no seletor

		if (!txtCodBar.getText().trim().equals("")) {
			seletor.setCodBar(txtCodBar.getText());
		}

		if (!txtNome.getText().trim().equals("")) {
			seletor.setNomeRemedio(txtNome.getText());
		}

		if (!txtComposicao.getText().trim().equals("")) {
			seletor.setComposicaoRemedio(txtNome.getText());
		}

		if (cmbFormaUso.getSelectedIndex() > -1) {
			seletor.setTipoRemedio(cmbFormaUso.getSelectedItem().toString());
		} else {
			seletor.setTipoRemedio("");
		}

		// if (chckbxGenerico.isSelected()) {
		// seletor.setGenerico(true);
		// }

		remedios = controlador.listarRemedios(seletor);
		atualizarTabelaMedicamentos(remedios);
	}

	private void consultarFormaUso() {
		ControllerRemedio controllerRemedio = new ControllerRemedio();
		listaFormasUso = controllerRemedio.consultarFormaUso();
	}

	private void atualizarTabelaMedicamentos(List<Remedio> remedios) {
		// atualiza o atributo remediosConsultados
		remediosConsultados = remedios;

		btnGerarXls.setEnabled(remedios != null && remedios.size() > 0);

		// Limpa a tabela
		tblRemedios.setModel(new DefaultTableModel(
				new String[][] { { "Código de Barras", "Dosagem", "Composição", "Generico", "Nome", "Data Cad.",
						"Preço", "Estoque", "Forma Uso", "Laboratorio" }, },
				new String[] { "Código de Barras", "Dosagem", "Composição", "Generico", "Nome", "Data Cad.", "Preço",
						"Estoque", "Forma Uso", "Laboratorio" }));

		DefaultTableModel modelo = (DefaultTableModel) tblRemedios.getModel();

		for (Remedio remedio : remedios) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do remedio
			// na ORDEM do cabeçalho da tabela

			/*
			 * String isGenerico = ""; if (remedio.isGenerico() == true) { isGenerico =
			 * "Sim"; } else { isGenerico = "Não"; }
			 */
			String[] novaLinha = new String[] { remedio.getCodBarra() + "", remedio.getDosagem(),
					remedio.getComposicao(), String.valueOf(remedio.isGenerico()), remedio.getNome(),
					String.valueOf(remedio.getDataCadastro()), "R$" + remedio.getPreco(), "" + remedio.getEstoque(),
					remedio.getFormaUso().getDescricao(), remedio.getLaboratorio().getNomeLaboratorio() };
			modelo.addRow(novaLinha);
		}
	}
}
