package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerRemedio;
import model.seletor.RemedioSeletor;
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

	private ArrayList<String> listaFormasUso;

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
		setTitle("Pesquisa de Remedios");
		setClosable(true);
		// TODO remover!
		remediosConsultados = new ArrayList<Remedio>();
		Remedio remedioTeste = new Remedio("0011", "Plasil", new Date(), 10.0, 50, "500", "�gua", "Tipo", false, "EMS");
		remediosConsultados.add(remedioTeste);

		setBounds(100, 100, 680, 540);
		getContentPane()
				.setLayout(new MigLayout("", "[211.00,grow][][grow]", "[][][][][][][][][][][10px:n][][][grow]"));

		JLabel lblCodbarras = new JLabel("c\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 0 0");

		JLabel lblespaco2 = new JLabel("      ");
		getContentPane().add(lblespaco2, "cell 1 0");

		tblRemedios = new JTable();
		tblRemedios.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		tblRemedios.setColumnSelectionAllowed(true);
		tblRemedios.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Composi��o",
				"Dosagem", "Tipo", "Generico", "Preco", "Estoque", "Laboratorio" }));
		getContentPane().add(tblRemedios, "cell 2 0 1 14,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 0 1,growx,aligny center");
		txtCodBar.setColumns(10);

		JLabel lblNome = new JLabel("nome:");
		getContentPane().add(lblNome, "cell 0 2");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 3,growx");
		txtNome.setColumns(10);

		JLabel lblComposicao = new JLabel("composi\u00E7\u00E3o:");
		getContentPane().add(lblComposicao, "cell 0 4");

		txtComposicao = new JTextField();
		getContentPane().add(txtComposicao, "cell 0 5,growx");
		txtComposicao.setColumns(10);

		JLabel lblFormaUso = new JLabel("forma de Uso:");
		getContentPane().add(lblFormaUso, "cell 0 6");

		this.consultarFormaUso();

		cmbFormaUso = new JComboBox(listaFormasUso.toArray());
		cmbFormaUso.setBackground(Color.WHITE);
		getContentPane().add(cmbFormaUso, "cell 0 7,growx");
		cmbFormaUso.setSelectedIndex(-1);

		JButton btnPesquisar = new JButton("pesquisar");
		btnPesquisar.setPreferredSize(new Dimension(80, 30));
		btnPesquisar.setBorder(new LineBorder(Color.gray, 2, true));
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarMedicamentos();
			}
		});

		JCheckBox chckbxGenerico_1 = new JCheckBox("gen\u00E9rico");
		chckbxGenerico_1.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxGenerico_1.setBackground(Color.WHITE);
		getContentPane().add(chckbxGenerico_1, "flowx,cell 0 8,alignx center");
		getContentPane().add(btnPesquisar, "cell 0 9,growx");

		btnGerarXls = new JButton("relatorio");
		btnGerarXls.setPreferredSize(new Dimension(100, 30));
		btnGerarXls.setBorder(new LineBorder(Color.gray, 2, true));
		btnGerarXls.setBackground(Color.WHITE);
		btnGerarXls.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JButton btnAlterar = new JButton("alterar");
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

		JButton btnExcluir = new JButton("excluir");
		btnExcluir.setPreferredSize(new Dimension(30, 30));
		btnExcluir.setBorder(new LineBorder(Color.gray, 2, true));
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mensagem = "";
				int remedioSelecionado = (int) tblRemedios.getValueAt(tblRemedios.getSelectedRow(), 1);

				ControllerRemedio controllerRemedio = new ControllerRemedio();

				if (controllerRemedio.existeRemedioPorCodBar(remedioSelecionado)) {
					mensagem = "Remedio n�o foi cadastrado";
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

		if (txtCodBar != null) {
			seletor.setCodBar(Integer.parseInt(txtCodBar.getText()));
		}

		if (!txtNome.getText().trim().equals("")) {
			seletor.setNomeRemedio(txtNome.getText());
		}

		if (!txtComposicao.getText().trim().equals("")) {
			seletor.setComposicaoRemedio(txtNome.getText());
		}

		if (cmbFormaUso.getSelectedIndex() > -1) {
			seletor.setTipoRemedio(cmbFormaUso.getSelectedItem().toString());
		}

		if (chckbxGenerico.isSelected()) {
			seletor.setGenerico(true);
		}

		// TODO descomentar!
		// remedios = controlador.listarRemedios(seletor);
		atualizarTabelaMedicamentos(this.remediosConsultados);
	}

	private void consultarFormaUso() {
		ControllerRemedio controllerRemedio = new ControllerRemedio();
		listaFormasUso = controllerRemedio.consultarFormaUso();

		// fazer retornar listaFormaUso do RemedioDAO!
		// ap�s isto, descomentar linha 105
	}

	private void atualizarTabelaMedicamentos(List<Remedio> remedios) {
		// atualiza o atributo remediosConsultados
		remediosConsultados = remedios;

		btnGerarXls.setEnabled(remedios != null && remedios.size() > 0);

		// Limpa a tabela
		tblRemedios.setModel(new DefaultTableModel(
				new String[][] { { "Codigo", "Nome", "Composi��o", "Dosagem", "Tipo", "Generico", "Preco", "Estoque",
						"Laboratorio" }, },
				new String[] { "Codigo", "Nome", "Composi��o", "Dosagem", "Tipo", "Generico", "Preco", "Estoque",
						"Laboratorio" }));

		DefaultTableModel modelo = (DefaultTableModel) tblRemedios.getModel();

		for (Remedio remedio : remedios) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do remedio
			// na ORDEM do cabe�alho da tabela

			String[] novaLinha = new String[] { remedio.getCodBarra() + "", remedio.getNome(), remedio.getComposicao(),
					remedio.getDosagem(), remedio.getFormaUso(), "generico?", "R$" + remedio.getPreco(), // TODO
																											// generico??
					"" + remedio.getEstoque(), remedio.getLaboratorio() };
			modelo.addRow(novaLinha);
		}
	}
}
