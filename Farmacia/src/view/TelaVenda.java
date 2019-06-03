package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class TelaVenda extends JInternalFrame {
	private JTextField txtCodBar;
	private JTextField txtNome;
	private JTable tblPesquisa;
	private JTable tblVenda;

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
		setBounds(100, 100, 527, 438);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[21.00][][][][][][28.00][27.00][]"));

		JLabel lblCodbarra = new JLabel("cod.barra");
		getContentPane().add(lblCodbarra, "cell 0 0");

		tblVenda = new JTable();
		tblVenda.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "nome", "quantidade", "preco" }));
		getContentPane().add(tblVenda, "cell 1 0 1 7,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 0 1,growx");
		txtCodBar.setColumns(10);

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "flowx,cell 0 2");

		tblPesquisa = new JTable();
		tblPesquisa.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "cod.barras", "nome", "preco" }));
		getContentPane().add(tblPesquisa, "cell 0 3 1 5,grow");

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		JLabel lblTotal = new JLabel("Total:");
		getContentPane().add(lblTotal, "flowx,cell 1 7");

		JLabel lblQuantidade = new JLabel("Quantidade:");
		getContentPane().add(lblQuantidade, "flowx,cell 0 8");

		JSlider sliQuantidade = new JSlider();
		sliQuantidade.setMinimum(1);
		sliQuantidade.setMaximum(10);
		getContentPane().add(sliQuantidade, "cell 0 8");
		getContentPane().add(btnAddItem, "cell 0 8");

		JButton btnRemover = new JButton("Remover");
		getContentPane().add(btnRemover, "flowx,cell 1 8");

		JButton btnConcluirVenda = new JButton("Concluir Venda");
		getContentPane().add(btnConcluirVenda, "cell 1 8");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 2");
		txtNome.setColumns(10);

		JButton btnPesquisar = new JButton("pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		getContentPane().add(btnPesquisar, "cell 0 2");

		JLabel lblValor = new JLabel("");
		getContentPane().add(lblValor, "cell 1 7");

	}

}
