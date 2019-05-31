package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.miginfocom.swing.MigLayout;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[]", "[]"));

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnMedicamento = new JMenu("medicamento");
		menuBar.add(mnMedicamento);

		JMenuItem mntmCadastrar = new JMenuItem("cadastrar");
		mnMedicamento.add(mntmCadastrar);

		JMenuItem mntmPesquisar = new JMenuItem("pesquisar");
		mnMedicamento.add(mntmPesquisar);

		JMenu mnProdutos = new JMenu("produtos");
		menuBar.add(mnProdutos);

		JMenuItem mntmCadastrar_1 = new JMenuItem("cadastrar");
		mnProdutos.add(mntmCadastrar_1);

		JMenuItem mntmPesquisar_1 = new JMenuItem("pesquisar");
		mnProdutos.add(mntmPesquisar_1);

		JMenu mnVendas = new JMenu("vendas");
		menuBar.add(mnVendas);

		JMenu mnSobre = new JMenu("sobre");
		menuBar.add(mnSobre);
	}

}
