package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.vo.Remedio;
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
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[21.00][][][][][][28.00][27.00]"));

		JLabel lblCodbarra = new JLabel("cod.barra");
		getContentPane().add(lblCodbarra, "cell 0 0");

		tblVenda = new JTable();
		tblVenda.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "nome", "quantidade", "preco" }));
		getContentPane().add(tblVenda, "cell 1 0 1 8,grow");

		txtCodBar = new JTextField();
		getContentPane().add(txtCodBar, "cell 0 1,growx");
		txtCodBar.setColumns(10);

		JButton btnPesquisar = new JButton("pesquisar");
		getContentPane().add(btnPesquisar, "cell 0 2");

		JLabel lblListaDeProdutos = new JLabel("lista de produtos");
		getContentPane().add(lblListaDeProdutos, "cell 0 3");

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "flowx,cell 0 4");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 4");
		txtNome.setColumns(10);

		JButton btnPesquisar_1 = new JButton("pesquisar");
		btnPesquisar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().add(btnPesquisar_1, "cell 0 4");

		tblPesquisa = new JTable();
		tblPesquisa.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "cod.barras", "nome", "preco" }));
		getContentPane().add(tblPesquisa, "cell 0 5,grow");

		JButton btnAddItem = new JButton("Add Item");
		btnAddItem.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		getContentPane().add(btnAddItem, "cell 0 7");

	}

}
