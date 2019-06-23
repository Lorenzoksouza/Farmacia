package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerVenda;
import model.seletor.MercadoriaSeletor;
import model.vo.ItemProduto;
import model.vo.ItemRemedio;
import model.vo.Mercadoria;
import model.vo.Produto;
import model.vo.Remedio;
import net.miginfocom.swing.MigLayout;

public class TelaVenda extends JInternalFrame {
	private JTextField txtCodBar;
	private JTextField txtNome;
	private JTable tblPesquisa;
	private JTable tblVenda;

	private List<Mercadoria> mercadoriasConsultadas = new ArrayList<Mercadoria>();
	private List<Mercadoria> mercadoriasParaVenda = new ArrayList<Mercadoria>();

	private List<ItemProduto> itensProdutos = new ArrayList<ItemProduto>();
	private List<ItemRemedio> itensRemedios = new ArrayList<ItemRemedio>();
	private List<Produto> produtos = new ArrayList<Produto>();

	private double valorTotal = 0.0;
	private JLabel lblValor;
	private JSpinner spiQuantidade;

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
		getContentPane().setBackground(Color.WHITE);
		setResizable(true);
		setFrameIcon(new ImageIcon(TelaVenda.class.getResource("/icons/vendas3x.png")));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setBackground(Color.WHITE);
		setTitle("Vendas");
		setClosable(true);
		setBounds(100, 100, 660, 530);
		getContentPane().setLayout(new MigLayout("", "[grow][][grow]", "[21.00][][][25.00][][][][][][grow][][]"));

		JLabel lblCodbarra = new JLabel("C\u00F3d.barra:");
		getContentPane().add(lblCodbarra, "flowx,cell 0 0,growx");

		JLabel lblEspaco = new JLabel("         ");
		getContentPane().add(lblEspaco, "cell 1 0,alignx left");

		tblVenda = new JTable();
		tblVenda.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		tblVenda.setFillsViewportHeight(true);
		tblVenda.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Quantidade", "Preço" }));
		getContentPane().add(tblVenda, "cell 2 0 1 10,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "flowx,cell 0 1,growx");
		txtCodBar.setColumns(10);

		tblPesquisa = new JTable();
		tblPesquisa.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		tblPesquisa.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Nome", "Preco", "Estoque" }));
		getContentPane().add(tblPesquisa, "cell 0 3 1 7,grow");

		JButton btnAddItem = new JButton("Adicionar item");
		btnAddItem.setBackground(Color.WHITE);
		btnAddItem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddItem.setPreferredSize(new Dimension(90, 30));
		btnAddItem.setBorder(new LineBorder(Color.gray, 2, true));
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblPesquisa.getSelectedRow() > 0) {
					Mercadoria mercadoriaSelecionada = mercadoriasConsultadas.get(tblPesquisa.getSelectedRow() - 1);
					int qtd = (int) spiQuantidade.getValue();
					mercadoriaSelecionada.setEstoque(qtd);
					mercadoriasParaVenda.add(mercadoriaSelecionada);

					adicionarItem(mercadoriaSelecionada);
					atualizarTblVenda(mercadoriasParaVenda);

					valorTotal += mercadoriaSelecionada.getPreco() * qtd;
					lblValor.setText("R$" + valorTotal);

					spiQuantidade.setValue(1);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um item para adicionar a venda");
				}
			}
		});

		JLabel lblTotal = new JLabel("Total:");
		getContentPane().add(lblTotal, "flowx,cell 2 10,aligny bottom");

		lblValor = new JLabel("R$0.00");
		getContentPane().add(lblValor, "cell 2 10,aligny bottom");

		JLabel lblQuantidade = new JLabel("Quantidade:");
		getContentPane().add(lblQuantidade, "flowx,cell 0 11,aligny center");

		spiQuantidade = new JSpinner();
		spiQuantidade.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		getContentPane().add(spiQuantidade, "cell 0 11,aligny center");
		getContentPane().add(btnAddItem, "cell 0 11,alignx center,aligny bottom");

		JButton btnRemover = new JButton("Remover");
		btnRemover.setForeground(Color.RED);
		btnRemover.setBackground(Color.WHITE);
		btnRemover.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRemover.setPreferredSize(new Dimension(100, 30));
		btnRemover.setBorder(new LineBorder(Color.gray, 2, true));
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tblVenda.getSelectedRow() > 0) {
					Mercadoria mercadoriaSelecionada = mercadoriasParaVenda.get(tblVenda.getSelectedRow() - 1);
					mercadoriasParaVenda.remove(mercadoriaSelecionada);

					int qtd = Integer.parseInt(tblVenda.getValueAt(tblVenda.getSelectedRow(), 1).toString());

					removerMercadoria(mercadoriaSelecionada);
					atualizarTblVenda(mercadoriasParaVenda);

					valorTotal -= mercadoriaSelecionada.getPreco() * qtd;
					lblValor.setText("R$" + valorTotal);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione um item para excluir");
				}
			}
		});
		getContentPane().add(btnRemover, "flowx,cell 2 11,alignx center,aligny bottom");

		JLabel lblEspaco2 = new JLabel("                             ");
		getContentPane().add(lblEspaco2, "cell 2 11");

		JButton btnConcluirVenda = new JButton("Concluir Venda");
		btnConcluirVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String mensagemConcluida = "Venda concluida";
				ControllerVenda controllerVenda = new ControllerVenda();
				String mensagem = controllerVenda.salvarVenda(valorTotal, itensProdutos, itensRemedios);
				JOptionPane.showMessageDialog(null, mensagemConcluida);
			}
		});
		btnConcluirVenda.setForeground(new Color(0, 128, 0));
		btnConcluirVenda.setBackground(Color.WHITE);
		btnConcluirVenda.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConcluirVenda.setPreferredSize(new Dimension(30, 30));
		btnConcluirVenda.setBorder(new LineBorder(Color.gray, 2, true));
		getContentPane().add(btnConcluirVenda, "cell 2 11,alignx center");

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setPreferredSize(new Dimension(200, 30));
		btnPesquisar.setBorder(new LineBorder(Color.gray, 2, true));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarMercadorias();
			}
		});
		getContentPane().add(btnPesquisar, "cell 0 2,alignx right");

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 0,growx");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 1,growx");
		txtNome.setColumns(10);

	}

	private void pesquisarMercadorias() {
		// lblPaginaAtual.setText(paginaAtual + "");

		ControllerVenda controlador = new ControllerVenda();
		MercadoriaSeletor seletor = new MercadoriaSeletor();

		// TODO descomentar
		List<Mercadoria> mercadorias = controlador.listarMercadorias(seletor);

//		seletor.setLimite(5);

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
			seletor.setCodBar(txtCodBar.getText());
		}

		if (!txtNome.getText().trim().equals("")) {
			seletor.setNome(txtNome.getText());
		}

		// mercadorias = controlador.listarVendaDTO(seletor);
		mercadorias = controlador.listarMercadorias(seletor);
		atualizarTabelaMercadorias(mercadorias);
	}

	private void atualizarTabelaMercadorias(List<Mercadoria> mercadorias) {
		// atualiza o atributo merdacoriasConsultadas
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

	private void removerMercadoria(Mercadoria mercadoriaSelecionada) {
		if (mercadoriaSelecionada instanceof Produto) {
			ItemProduto itemProduto = obterItemProduto((Produto) mercadoriaSelecionada);
			itensProdutos.remove(itemProduto);
		}

		if (mercadoriaSelecionada instanceof Remedio) {
			ItemRemedio itemRemedio = obterItemRemedio((Remedio) mercadoriaSelecionada);
			itensRemedios.remove(itemRemedio);
		}
	}

	private void adicionarItem(Mercadoria mercadoriaSelecionada) {
		int quantidade = (int) spiQuantidade.getValue();
		if (mercadoriaSelecionada instanceof Produto) {
			if (produtos.contains(mercadoriaSelecionada)) {
				ItemProduto item = obterItemProduto((Produto) mercadoriaSelecionada);
				item.setQuantidade(item.getQuantidade() + quantidade);
			} else {
				ItemProduto novoItem = new ItemProduto();
				novoItem.setProduto((Produto) mercadoriaSelecionada);
				novoItem.setQuantidade(quantidade);

				itensProdutos.add(novoItem);
			}
		}

		if (mercadoriaSelecionada instanceof Remedio) {
			if (produtos.contains(mercadoriaSelecionada)) {
				ItemRemedio item = obterItemRemedio((Remedio) mercadoriaSelecionada);
				item.setQuantidade(item.getQuantidade() + quantidade);
			} else {
				ItemRemedio novoItem = new ItemRemedio();
				novoItem.setRemedio((Remedio) mercadoriaSelecionada);
				novoItem.setQuantidade(quantidade);

				itensRemedios.add(novoItem);
			}
		}
	}

	private ItemRemedio obterItemRemedio(Remedio remedioSelecionado) {
		ItemRemedio item = null;

		for (ItemRemedio i : itensRemedios) {
			if (i.getRemedio().getCodBarra().equals(remedioSelecionado.getCodBarra())) {
				item = i;
				break;
			}
		}
		return item;
	}

	private ItemProduto obterItemProduto(Produto produtoSelecionado) {
		ItemProduto item = null;

		for (ItemProduto i : itensProdutos) {
			if (i.getProduto().getCodBarra().equals(produtoSelecionado.getCodBarra())) {
				item = i;
				break;
			}
		}
		return item;
	}

	private void atualizarTblVenda(List<Mercadoria> mercadoriasParaVenda) {
		tblVenda.setModel(new DefaultTableModel(new String[][] { { "Nome", "Quantidade", "Preço" } },
				new String[] { "Nome", "Quantidade", "Preço" }));

		DefaultTableModel modelo = (DefaultTableModel) tblVenda.getModel();

		for (Mercadoria mercadoria : mercadoriasParaVenda) {
			String[] novaLinha = new String[] { mercadoria.getNome(), mercadoria.getEstoque() + "",
					"R$" + mercadoria.getPreco() };
			modelo.addRow(novaLinha);
		}
	}
}
