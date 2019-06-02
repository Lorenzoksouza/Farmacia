package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.miginfocom.swing.MigLayout;

public class Menu {

	private JFrame frame;

	private JDesktopPane desktopPane;
	CadastroMedicamento cadastroMedicamento = null;
	ListagemMedicamento pesquisaMedicamento = null;
	CadastroProduto cadastroProduto = null;
	ListagemProduto pesquisaProduto = null;
	TelaVenda telaVenda = null;

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
		frame.getContentPane().setLayout(new MigLayout("", "[grow][]", "[grow][]"));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(desktopPane, "cell 0 0 2 2,grow");

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnMedicamento = new JMenu("medicamento");
		menuBar.add(mnMedicamento);

		JMenuItem mntmCadastrarRemedio = new JMenuItem("cadastrar");
		mntmCadastrarRemedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checarComponentesTelaPrincipal(cadastroMedicamento)) {
				} else {
					cadastroMedicamento = new CadastroMedicamento(null);
					desktopPane.add(cadastroMedicamento);
					// desktopPane.getDesktopManager().maximizeFrame(cadastroMedicamento);
					cadastroMedicamento.show();
				}
			}
		});
		mnMedicamento.add(mntmCadastrarRemedio);

		JMenuItem mntmPesquisarRemedio = new JMenuItem("pesquisar");
		mntmPesquisarRemedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checarComponentesTelaPrincipal(pesquisaMedicamento)) {
				} else {
					pesquisaMedicamento = new ListagemMedicamento();
					desktopPane.add(pesquisaMedicamento);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					pesquisaMedicamento.show();
				}
			}
		});
		mnMedicamento.add(mntmPesquisarRemedio);

		JMenu mnProdutos = new JMenu("produtos");
		menuBar.add(mnProdutos);

		JMenuItem mntmCadastrarProduto = new JMenuItem("cadastrar");
		mntmCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checarComponentesTelaPrincipal(pesquisaMedicamento)) {
				} else {
					pesquisaMedicamento = new ListagemMedicamento();
					desktopPane.add(pesquisaMedicamento);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					pesquisaMedicamento.show();
				}
			}
		});
		mnProdutos.add(mntmCadastrarProduto);

		JMenuItem mntmPesquisarProduto = new JMenuItem("pesquisar");
		mntmPesquisarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checarComponentesTelaPrincipal(pesquisaProduto)) {
				} else {
					pesquisaProduto = new ListagemProduto();
					desktopPane.add(pesquisaProduto);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					pesquisaProduto.show();
				}
			}
		});
		mnProdutos.add(mntmPesquisarProduto);

		JMenu mnVendas = new JMenu("vendas");
		mnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checarComponentesTelaPrincipal(telaVenda)) {
				} else {
					telaVenda = new TelaVenda();
					desktopPane.add(telaVenda);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					telaVenda.show();
				}
			}
		});
		menuBar.add(mnVendas);

		JMenu mnSobre = new JMenu("sobre");
		menuBar.add(mnSobre);
	}

	public boolean checarComponentesTelaPrincipal(Object frame) {
		ArrayList<Component> componentes = new ArrayList<Component>(Arrays.asList(desktopPane.getComponents()));
		if (componentes.contains(frame)) {
			return true;
		} else {
			return false;
		}
	}

	public void chamarPai(String telaFilho) {
		JInternalFrame tela = new JInternalFrame();
		tela.setName(telaFilho);
		tela = null;
	}

}
