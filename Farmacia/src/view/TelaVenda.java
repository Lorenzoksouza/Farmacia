package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.ControllerVenda;
import model.seletor.MercadoriaSeletor;
import model.vo.Mercadoria;
import model.vo.Produto;
import net.miginfocom.swing.MigLayout;

public class TelaVenda extends JInternalFrame {
	private JTextField txtCodBar;
	private JTextField txtNome;
	private JTable tblPesquisa;
	private JTable tblVenda;

	private List<Mercadoria> mercadoriasConsultadas;
	private JLabel lblValor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVenda frame = new TelaVenda();
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
	public TelaVenda() {
		setTitle("Vendas");
		setClosable(true);
		setBounds(100, 100, 600, 390);
		getContentPane()
				.setLayout(new MigLayout("", "[grow][grow]", "[21.00][][][25.00][][][][][][][][][grow,bottom]"));

		JLabel lblCodbarra = new JLabel("cod.barra");
		getContentPane().add(lblCodbarra, "cell 0 0");

		tblVenda = new JTable();
		tblVenda.setFillsViewportHeight(true);
		tblVenda.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "nome", "quantidade", "preco" }));
		getContentPane().add(tblVenda, "cell 1 0 1 11,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 0 1,growx");
		txtCodBar.setColumns(10);

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "flowx,cell 0 2");

		tblPesquisa = new JTable();
		tblPesquisa.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Preco", "Estoque" }));
		getContentPane().add(tblPesquisa, "cell 0 3 1 9,grow");

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAddItem.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mercadoria mercadoria = new Produto();

				DefaultTableModel modelo = (DefaultTableModel) tblVenda.getModel();

				mercadoria = mercadoriasConsultadas.get(tblPesquisa.getSelectedRow() + 1);
				String[] novaLinha = new String[] { mercadoria.getCodBarra() + "", mercadoria.getNome(),
						"R$" + mercadoria.getPreco(), "" + mercadoria.getEstoque(), };
				modelo.addRow(novaLinha);

				lblValor.setText("R$" + (Double.parseDouble(lblValor.getText()) + mercadoria.getPreco()));
			}
		});

		JLabel lblTotal = new JLabel("Total:");
		getContentPane().add(lblTotal, "flowx,cell 1 11,aligny bottom");

		JLabel lblQuantidade = new JLabel("Quantidade:");
		getContentPane().add(lblQuantidade, "flowx,cell 0 12,aligny bottom");

		JSpinner spiQuantidade = new JSpinner();
		spiQuantidade.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		getContentPane().add(spiQuantidade, "cell 0 12,aligny bottom");
		getContentPane().add(btnAddItem, "cell 0 12,aligny bottom");

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Mercadoria mercadoria = new Produto();

				DefaultTableModel modelo = (DefaultTableModel) tblVenda.getModel();
				modelo.removeRow(tblVenda.getSelectedRow());

				lblValor.setText("R$" + (Double.parseDouble(lblValor.getText()) - mercadoria.getPreco()));
			}
		});
		getContentPane().add(btnRemover, "flowx,cell 1 12,alignx leading,aligny bottom");

		JButton btnConcluirVenda = new JButton("Concluir Venda");
		getContentPane().add(btnConcluirVenda, "cell 1 12,alignx leading,aligny bottom");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 2");
		txtNome.setColumns(10);

		JButton btnPesquisar = new JButton("pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarMercadorias();
			}
		});
		getContentPane().add(btnPesquisar, "cell 0 2");

		JLabel lblValor = new JLabel("R$0.00");
		getContentPane().add(lblValor, "cell 1 11,aligny bottom");

	}

	private void pesquisarMercadorias() {
		// lblPaginaAtual.setText(paginaAtual + "");

		ControllerVenda controlador = new ControllerVenda();
		MercadoriaSeletor seletor = new MercadoriaSeletor();

		// TODO descomentar
		List<Mercadoria> mercadorias = controlador.listarMercadorias(seletor);

		seletor.setLimite(5);

//		int quociente = remedios.size() / seletor.getLimite();
//		int resto = remedios.size() % seletor.getLimite();
//
//		if (resto == 0) {
//			totalPaginas = quociente;
//		} else {
//			totalPaginas = quociente + 1;
//		}
//		lblTotalPaginas.setText(totalPaginas + "");
//
//		seletor.setPagina(paginaAtual);

		// Preenche os campos de filtro da tela no seletor

		if (txtCodBar != null) {
			seletor.setCodBar(Integer.parseInt(txtCodBar.getText()));
		}

		if (!txtNome.getText().trim().equals("")) {
			seletor.setNome(txtNome.getText());
		}

		mercadorias = controlador.listarMercadorias(seletor);
		atualizarTabelaMedicamentos(this.mercadoriasConsultadas);
	}

	private void atualizarTabelaMedicamentos(List<Mercadoria> mercadorias) {
		// atualiza o atributo remediosConsultados
		mercadoriasConsultadas = mercadorias;

		// btnGerarXls.setEnabled(remedios != null && remedios.size() > 0);

		// Limpa a tabela
		tblPesquisa.setModel(new DefaultTableModel(new String[][] { { "Codigo", "Nome", "Preco", "Estoque" }, },
				new String[] { "Codigo", "Nome", "Preco", "Estoque" }));

		DefaultTableModel modelo = (DefaultTableModel) tblPesquisa.getModel();

		for (Mercadoria mercadoria : mercadorias) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do remedio
			// na ORDEM do cabeçalho da tabela

			String[] novaLinha = new String[] { mercadoria.getCodBarra() + "", mercadoria.getNome(),
					"R$" + mercadoria.getPreco(), "" + mercadoria.getEstoque(), };
			modelo.addRow(novaLinha);
		}
	}
}
