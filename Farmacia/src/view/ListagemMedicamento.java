package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
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
		setTitle("Pesquisa de Remedios");
		setClosable(true);
		// TODO remover!
		remediosConsultados = new ArrayList<Remedio>();
		Remedio remedioTeste = new Remedio("0011", "Plasil", new Date(), 10.0, 50, "500", "Água", "Tipo", false, "EMS");
		remediosConsultados.add(remedioTeste);

		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][][grow]", "[grow][][][][][][][][][][][][grow]"));

		JLabel lblCodbarras = new JLabel("cod.barras");
		getContentPane().add(lblCodbarras, "cell 0 0");

		tblRemedios = new JTable();
		tblRemedios.setColumnSelectionAllowed(true);
		tblRemedios.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Composição",
				"Dosagem", "Tipo", "Generico", "Preco", "Estoque", "Laboratorio" }));
		getContentPane().add(tblRemedios, "cell 2 0 1 12,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 0 1,growx");
		txtCodBar.setColumns(10);

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "cell 0 2");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 3,growx");
		txtNome.setColumns(10);

		JLabel lblComposicao = new JLabel("composicao");
		getContentPane().add(lblComposicao, "cell 0 4");

		txtComposicao = new JTextField();
		getContentPane().add(txtComposicao, "cell 0 5,growx");
		txtComposicao.setColumns(10);

		JLabel lblFormaUso = new JLabel("Forma de Uso");
		getContentPane().add(lblFormaUso, "cell 0 6");

		this.consultarFormaUso();

		cmbFormaUso = new JComboBox(listaFormasUso.toArray());
		getContentPane().add(cmbFormaUso, "cell 0 7,growx");
		cmbFormaUso.setSelectedIndex(-1);

		JCheckBox chckbxGenerico_1 = new JCheckBox("generico");
		getContentPane().add(chckbxGenerico_1, "cell 0 9");

		JButton btnPesquisar = new JButton("pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarMedicamentos();
			}
		});
		getContentPane().add(btnPesquisar, "cell 1 9");

		JButton btnExcluir = new JButton("excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mensagem = "";
				int remedioSelecionado = (int) tblRemedios.getValueAt(tblRemedios.getSelectedRow(), 1);

				ControllerRemedio controllerRemedio = new ControllerRemedio();

				if (controllerRemedio.existeRemedioPorCodBar(remedioSelecionado)) {
					mensagem = "Remedio não foi cadastrado";
				} else {
					mensagem = controllerRemedio.excluir(remedioSelecionado);
				}
			}
		});
		getContentPane().add(btnExcluir, "cell 0 10");

		JButton btnAlterar = new JButton("alterar");
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
		getContentPane().add(btnAlterar, "cell 1 10");

		btnGerarXls = new JButton("relatorio");
		btnGerarXls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		getContentPane().add(btnGerarXls, "cell 0 11");

	}

	private void pesquisarMedicamentos() {
		// lblPaginaAtual.setText(paginaAtual + "");

		ControllerRemedio controlador = new ControllerRemedio();
		RemedioSeletor seletor = new RemedioSeletor();

		// TODO descomentar
		// List<Remedio> remedios = controlador.listarRemedios(seletor);

		seletor.setLimite(10);

//		int quociente = remedios.size() / seletor.getLimite();
//		int resto = remedios.size() % seletor.getLimite();
//
//		if (resto == 0) {
//			totalPaginas = quociente;
//		} else {
//			totalPaginas = quociente + 1;
//		}
//		// lblTotalPaginas.setText(totalPaginas + "");
//
//		seletor.setPagina(paginaAtual);

		// Preenche os campos de filtro da tela no seletor

//		if (txtCodBar != null) {
//			seletor.setCodBar(Integer.parseInt(txtCodBar.getText()));
//		}
//
//		if (!txtNome.getText().trim().equals("")) {
//			seletor.setNomeRemedio(txtNome.getText());
//		}
//
//		if (!txtComposicao.getText().trim().equals("")) {
//			seletor.setComposicaoRemedio(txtNome.getText());
//		}
//
//		if (cmbTipo.getSelectedIndex() > 0) {
//			seletor.setTipoRemedio(cmbTipo.getSelectedItem().toString());
//		}
//
//		if (chckbxGenerico.isSelected()) {
//			seletor.setGenerico(true);
//		}

		// TODO descomentar!
		// remedios = controlador.listarRemedios(seletor);
		atualizarTabelaMedicamentos(this.remediosConsultados);
	}

	private void consultarFormaUso() {
		ControllerRemedio controllerRemedio = new ControllerRemedio();
		listaFormasUso = controllerRemedio.consultarFormaUso();

		// fazer retornar listaFormaUso do RemedioDAO!
		// após isto, descomentar linha 105
	}

	private void atualizarTabelaMedicamentos(List<Remedio> remedios) {
		// atualiza o atributo remediosConsultados
		remediosConsultados = remedios;

		btnGerarXls.setEnabled(remedios != null && remedios.size() > 0);

		// Limpa a tabela
		tblRemedios.setModel(new DefaultTableModel(
				new String[][] { { "Codigo", "Nome", "Composição", "Dosagem", "Tipo", "Generico", "Preco", "Estoque",
						"Laboratorio" }, },
				new String[] { "Codigo", "Nome", "Composição", "Dosagem", "Tipo", "Generico", "Preco", "Estoque",
						"Laboratorio" }));

		DefaultTableModel modelo = (DefaultTableModel) tblRemedios.getModel();

		for (Remedio remedio : remedios) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do remedio
			// na ORDEM do cabeçalho da tabela

			String[] novaLinha = new String[] { remedio.getCodBarra() + "", remedio.getNome(), remedio.getComposicao(),
					remedio.getDosagem(), remedio.getFormaUso(), "generico?", "R$" + remedio.getPreco(), // TODO
																											// generico??
					"" + remedio.getEstoque(), remedio.getLaboratorio() };
			modelo.addRow(novaLinha);
		}
	}
}
