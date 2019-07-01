package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.ControllerVenda;
import model.seletor.VendaSeletor;
import model.vo.Venda;
import net.miginfocom.swing.MigLayout;

public class ListagemVenda extends JInternalFrame {
	private JTable tblVendas;
	private List<Venda> vendasConsultadas;
	private int totalPaginas = 1;
	private int paginaAtual = 1;
	private JLabel lblPaginaAtual;
	private JLabel lbMax;
	private int paginaTotal = 1;
	private JButton btnProximo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListagemVenda frame = new ListagemVenda();
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
	public ListagemVenda() {
		setTitle("Relatório de venda");
		setFrameIcon(new ImageIcon(ListagemVenda.class.getResource("/icons/cart (2).png")));
		setResizable(true);
		setBorder(new LineBorder(new Color(192, 192, 192), 3));
		setClosable(true);
		setBounds(100, 100, 600, 470);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[grow][][]"));

		JLabel lblEspaco = new JLabel(" ");
		getContentPane().add(lblEspaco, "cell 0 0");

		tblVendas = new JTable();
		tblVendas.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "    Id", "Valor", "Data da venda", "Produtos", "Remedios" }));
		tblVendas.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		getContentPane().add(tblVendas, "flowx,cell 1 0,grow");

		JButton btnProximo = new JButton("Proximo>");
		btnProximo.setBorder(new LineBorder(Color.gray, 2, true));
		btnProximo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnProximo.setBackground(Color.WHITE);
		btnProximo.setPreferredSize(new Dimension(80, 30));
		JButton btnAnterior = new JButton("<Anterior");
		btnAnterior.setBorder(new LineBorder(Color.gray, 2, true));
		btnAnterior.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnterior.setBackground(Color.WHITE);
		btnAnterior.setPreferredSize(new Dimension(80, 30));
		btnAnterior.setEnabled(false);
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (paginaAtual > 1) {
					paginaAtual--;
				}
				if (paginaAtual == 1) {
					btnAnterior.setEnabled(false);

				}
				btnProximo.setEnabled(true);
				listarVendas();
			}
		});
		getContentPane().add(btnAnterior, "flowx,cell 1 1,alignx center");

		JButton btnGerarLista = new JButton("Gerar lista");
		btnGerarLista.setIcon(new ImageIcon(ListagemVenda.class.getResource("/icons/search.png")));
		btnGerarLista.setBorder(new LineBorder(Color.gray, 2, true));
		btnGerarLista.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGerarLista.setBackground(Color.WHITE);
		btnGerarLista.setPreferredSize(new Dimension(80, 30));
		btnGerarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarVendas();
			}
		});
		getContentPane().add(btnGerarLista, "flowx,cell 1 2,alignx center");

		JButton btnRelatorio = new JButton("Relatorio");
		btnRelatorio.setBorder(new LineBorder(Color.gray, 2, true));
		btnRelatorio.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRelatorio.setBackground(Color.WHITE);
		btnRelatorio.setPreferredSize(new Dimension(80, 30));
		getContentPane().add(btnRelatorio, "cell 1 2,alignx center");

		lblPaginaAtual = new JLabel("");
		lblPaginaAtual.setText(paginaAtual + "");
		getContentPane().add(lblPaginaAtual, "cell 1 1,alignx center");

		JLabel label = new JLabel("/");
		getContentPane().add(label, "cell 1 1,alignx center");

		lbMax = new JLabel("1");
		getContentPane().add(lbMax, "cell 1 1,alignx center");

		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual++;
				if (paginaAtual == paginaTotal) {
					btnProximo.setEnabled(false);
				}
				btnAnterior.setEnabled(true);
				listarVendas();
			}
		});
		getContentPane().add(btnProximo, "cell 1 1,alignx center");

		JLabel lblEspaco_1 = new JLabel(" ");
		getContentPane().add(lblEspaco_1, "cell 1 0");

	}

	private void listarVendas() {
		lblPaginaAtual.setText(paginaAtual + "");

		ControllerVenda controlador = new ControllerVenda();
		VendaSeletor seletor = new VendaSeletor();

		List<Venda> vendas = controlador.listarVendas(seletor);

		seletor.setLimite(10);

		int quociente = vendas.size() / seletor.getLimite();
		int resto = vendas.size() % seletor.getLimite();

		if (resto == 0) {
			totalPaginas = quociente;
		} else {
			totalPaginas = quociente + 1;
		}
		lbMax.setText(totalPaginas + "");

		seletor.setPagina(paginaAtual);

		vendas = controlador.listarVendas(seletor);
		atualizarTabelaVendas(vendas);
	}

	private void atualizarTabelaVendas(List<Venda> vendas) {
		// atualiza o atributo remediosConsultados
		vendasConsultadas = vendas;

		// btnGerarXls.setEnabled(vendas != null && vendas.size() > 0);

		// Limpa a tabela
		tblVendas.setModel(new DefaultTableModel(
				new String[][] { { "    Id", "Valor", "Data da venda", "Produtos", "Remedios" }, },
				new String[] { "    Id", "Valor", "Data da venda", "Produtos", "Remedios" }));

		DefaultTableModel modelo = (DefaultTableModel) tblVendas.getModel();

		for (Venda venda : vendas) {
			// Crio uma nova linha na tabela
			// Preencher a linha com os atributos do remedio
			// na ORDEM do cabe�alho da tabela

			DecimalFormat format = new DecimalFormat("0.00");
			String[] novaLinha = new String[] { venda.getIdVenda() + "", format.format(venda.getValor()),
					String.valueOf(venda.getDataVenda()), String.valueOf(venda.getItemProduto()),
					String.valueOf(venda.getItemRemedio()) };
			modelo.addRow(novaLinha);
		}
	}
}
