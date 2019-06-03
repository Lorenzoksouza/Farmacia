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
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import net.miginfocom.swing.MigLayout;

public class Menu extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JDesktopPane desktopPane;
	CadastroMedicamento cadastroMedicamento = null;
	ListagemMedicamento pesquisaMedicamento = null;
	CadastroProduto cadastroProduto = null;
	ListagemProduto pesquisaProduto = null;
	TelaVenda telaVenda = null;
	TelaSobre telaSobre = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu menu = new Menu();
					menu.setVisible(true);
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
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new MigLayout("", "[grow][]", "[grow][]"));

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.LIGHT_GRAY);
		this.getContentPane().add(desktopPane, "cell 0 0 2 2,grow");

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu mnMedicamento = new JMenu("medicamento");
		menuBar.add(mnMedicamento);

		JMenuItem mntmCadastrarRemedio = new JMenuItem("cadastrar");
		mntmCadastrarRemedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (temComponenteNaTela(cadastroMedicamento)) {
				} else {
					cadastroMedicamento = new CadastroMedicamento(null);
					desktopPane.add(cadastroMedicamento);
					// desktopPane.getDesktopManager().maximizeFrame(cadastroMedicamento);
					cadastroMedicamento.show();

					cadastroMedicamento.addInternalFrameListener(new InternalFrameAdapter() {
						@Override
						public void internalFrameClosing(InternalFrameEvent evt) {
							Menu pai = (Menu) SwingUtilities.getWindowAncestor(cadastroMedicamento);
							pai.chamarPai(cadastroMedicamento);
						}
					});
				}
			}
		});
		mnMedicamento.add(mntmCadastrarRemedio);

		JMenuItem mntmPesquisarRemedio = new JMenuItem("pesquisar");
		mntmPesquisarRemedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!temComponenteNaTela(pesquisaMedicamento)) {
					pesquisaMedicamento = new ListagemMedicamento();
					desktopPane.add(pesquisaMedicamento);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					pesquisaMedicamento.show();

					pesquisaMedicamento.addInternalFrameListener(new InternalFrameAdapter() {
						@Override
						public void internalFrameClosing(InternalFrameEvent evt) {
							Menu pai = (Menu) SwingUtilities.getWindowAncestor(pesquisaMedicamento);
							pai.chamarPai(pesquisaMedicamento);
						}
					});
				}
			}
		});
		mnMedicamento.add(mntmPesquisarRemedio);

		JMenu mnProdutos = new JMenu("produtos");
		menuBar.add(mnProdutos);

		JMenuItem mntmCadastrarProduto = new JMenuItem("cadastrar");
		mntmCadastrarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temComponenteNaTela(pesquisaMedicamento)) {
				} else {
					cadastroProduto = new CadastroProduto();
					desktopPane.add(cadastroProduto);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					cadastroProduto.show();

					cadastroProduto.addInternalFrameListener(new InternalFrameAdapter() {
						@Override
						public void internalFrameClosing(InternalFrameEvent evt) {
							Menu pai = (Menu) SwingUtilities.getWindowAncestor(cadastroProduto);
							pai.chamarPai(cadastroProduto);
						}
					});
				}
			}
		});
		mnProdutos.add(mntmCadastrarProduto);

		JMenuItem mntmPesquisarProduto = new JMenuItem("pesquisar");
		mntmPesquisarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temComponenteNaTela(pesquisaProduto)) {
				} else {
					pesquisaProduto = new ListagemProduto();
					desktopPane.add(pesquisaProduto);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					pesquisaProduto.show();

					pesquisaProduto.addInternalFrameListener(new InternalFrameAdapter() {
						@Override
						public void internalFrameClosing(InternalFrameEvent evt) {
							Menu pai = (Menu) SwingUtilities.getWindowAncestor(pesquisaProduto);
							pai.chamarPai(pesquisaProduto);
						}
					});
				}
			}
		});
		mnProdutos.add(mntmPesquisarProduto);

		JMenu mnVendas = new JMenu("vendas");
		mnVendas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temComponenteNaTela(telaVenda)) {
				} else {
					telaVenda = new TelaVenda();
					desktopPane.add(telaVenda);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					telaVenda.show();

					telaVenda.addInternalFrameListener(new InternalFrameAdapter() {
						@Override
						public void internalFrameClosing(InternalFrameEvent evt) {
							Menu pai = (Menu) SwingUtilities.getWindowAncestor(telaVenda);
							pai.chamarPai(telaVenda);
						}
					});
				}
			}
		});
		menuBar.add(mnVendas);

		JMenu mnSobre = new JMenu("sobre");
		mnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (temComponenteNaTela(telaSobre)) {
				} else {
					telaVenda = new TelaVenda();
					desktopPane.add(telaVenda);
					// desktopPane.getDesktopManager().maximizeFrame(pesquisaMedicamento);
					telaVenda.show();

					telaVenda.addInternalFrameListener(new InternalFrameAdapter() {
						@Override
						public void internalFrameClosing(InternalFrameEvent evt) {
							Menu pai = (Menu) SwingUtilities.getWindowAncestor(telaVenda);
							pai.chamarPai(telaVenda);
						}
					});
				}
			}
		});
		menuBar.add(mnSobre);
	}

	public boolean temComponenteNaTela(Object frame) {
		ArrayList<Component> componentes = new ArrayList<Component>(Arrays.asList(desktopPane.getComponents()));
		return (componentes.contains(frame));
	}

	public void chamarPai(JInternalFrame telaFilho) {
		telaFilho = null;
	}

}
