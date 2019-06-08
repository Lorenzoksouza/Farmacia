package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerProduto;
import model.seletor.ProdutoSeletor;
import model.vo.Produto;
import net.miginfocom.swing.MigLayout;

public class ListagemProduto extends JInternalFrame {
	CadastroProduto cadastroProduto = null;

	private JTextField txtCodBar;
	private JTextField txtNome;
	private JTable tblProdutos;
	private JComboBox cmbCategoria;
	private JButton btnGerarXls;

	private List<Produto> produtosConsultados;
	private int totalPaginas = 1;
	private int paginaAtual = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemProduto frame = new ListagemProduto();
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
	public ListagemProduto() {
		getContentPane().setBackground(Color.WHITE);
		setFrameIcon(new ImageIcon(ListagemProduto.class.getResource("/icons/prod3x.png")));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setTitle("Pesquisa de produtos");
		setClosable(true);
		setBounds(100, 100, 680, 540);
		getContentPane()
				.setLayout(new MigLayout("", "[211.00][][grow]", "[18.00][][][][][][8px:n][][10px:n][][][grow]"));

		JLabel lblCodbarras = new JLabel("C\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 0 0");

		JLabel lblEspaco = new JLabel("      ");
		getContentPane().add(lblEspaco, "cell 1 0");

		tblProdutos = new JTable();
		tblProdutos.setColumnSelectionAllowed(true);
		tblProdutos.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		tblProdutos.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Código", "Nome", "Preço", "Categoria", "Estoque" }));
		getContentPane().add(tblProdutos, "cell 2 0 1 12,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 0 1,growx");
		txtCodBar.setColumns(10);

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 2");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 3,growx");
		txtNome.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		getContentPane().add(lblCategoria, "cell 0 4");

		JComboBox cmbCategoria = new JComboBox();
		cmbCategoria.setBackground(Color.WHITE);
		getContentPane().add(cmbCategoria, "cell 0 5,growx");

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setBackground(Color.WHITE);
		btnPesquisar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPesquisar.setPreferredSize(new Dimension(80, 30));
		btnPesquisar.setBorder(new LineBorder(Color.gray, 2, true));

		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarProdutos();
			}
		});
		getContentPane().add(btnPesquisar, "cell 0 7,growx");

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBackground(Color.WHITE);
		btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExcluir.setPreferredSize(new Dimension(80, 30));
		btnExcluir.setBorder(new LineBorder(Color.gray, 2, true));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensagem = "";
				int produtoSelecionado = (int) tblProdutos.getValueAt(tblProdutos.getSelectedRow(), 1);

				ControllerProduto controllerProduto = new ControllerProduto();

				if (controllerProduto.existeProdutoCodBar(produtoSelecionado)) {
					mensagem = "Remedio não foi cadastrado";
				} else {
					mensagem = controllerProduto.excluir(produtoSelecionado);
				}
			}
		});
		getContentPane().add(btnExcluir, "flowx,cell 0 9,growx");

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAlterar.setPreferredSize(new Dimension(80, 30));
		btnAlterar.setBorder(new LineBorder(Color.gray, 2, true));
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produtoSelecionado = new Produto();

//				produtoSelecionado = produtosConsultados.get(tblProdutos.getSelectedRow() + 1);

				produtoSelecionado.setNome(produtosConsultados.get(tblProdutos.getSelectedRow() - 1).getNome());
				produtoSelecionado.setCodBarra(produtosConsultados.get(tblProdutos.getSelectedRow() - 1).getCodBarra());
				produtoSelecionado.setPreco(produtosConsultados.get(tblProdutos.getSelectedRow() - 1).getPreco());
				produtoSelecionado.setEstoque(produtosConsultados.get(tblProdutos.getSelectedRow() - 1).getEstoque());

				cadastroProduto = new CadastroProduto(produtoSelecionado);
				cadastroProduto.setVisible(true);

			}
		});
		getContentPane().add(btnAlterar, "cell 0 9,growx");

		JButton btnGerarXls_1 = new JButton("Relatorio");
		btnGerarXls_1.setBackground(Color.WHITE);
		btnGerarXls_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGerarXls_1.setPreferredSize(new Dimension(80, 30));
		btnGerarXls_1.setBorder(new LineBorder(Color.gray, 2, true));
		btnGerarXls_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		getContentPane().add(btnGerarXls_1, "cell 0 10,alignx center");

	}

	private void pesquisarProdutos() {
		// lblPaginaAtual.setText(paginaAtual + "");

		ControllerProduto controlador = new ControllerProduto();
		ProdutoSeletor seletor = new ProdutoSeletor();

		List<Produto> produtos = controlador.listarProdutos(seletor);

		seletor.setLimite(10);

		int quociente = produtos.size() / seletor.getLimite();
		int resto = produtos.size() % seletor.getLimite();

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
			seletor.setNomeProduto(txtNome.getText());
		}

		if (cmbCategoria.getSelectedIndex() > 0) {
			seletor.setCategoria(cmbCategoria.getSelectedItem().toString());
		}

		produtos = controlador.listarProdutos(seletor);
		atualizarTabelaProdutos(produtos);
	}

	private void atualizarTabelaProdutos(List<Produto> produtos) {
		// atualiza o atributo produtosConsultados
		produtosConsultados = produtos;

		btnGerarXls.setEnabled(produtos != null && produtos.size() > 0);

		// Limpa a tabela
		tblProdutos.setModel(
				new DefaultTableModel(new String[][] { { "Código", "Nome", "Preço", "Categoria", "Estoque" }, },
						new String[] { "Código", "Nome", "Preço", "Categoria", "Estoque" }));

		DefaultTableModel modelo = (DefaultTableModel) tblProdutos.getModel();

		for (Produto produto : produtos) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do remedio
			// na ORDEM do cabeçalho da tabela

			String[] novaLinha = new String[] { produto.getCodBarra() + "", produto.getNome(),
					"R$" + produto.getPreco(), produto.getCategoria(), produto.getEstoque() + "" };
			modelo.addRow(novaLinha);
		}
	}

}
