package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.ControllerProduto;
import model.vo.Categoria;
import model.vo.Produto;
import net.miginfocom.swing.MigLayout;
import util.JNumberFormatField;
import util.JTextFieldLimit;

public class CadastroProduto extends JInternalFrame {
	private JTextField txtNome;
	private JNumberFormatField txtPreco;
	private JTextField txtEstoque;
	private JTextField txtCodBar;
	private JComboBox<Categoria> cmbCategoria;

	private ArrayList<Categoria> listaCategorias;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto frame = new CadastroProduto();
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
	public CadastroProduto() {
		setBorder(new LineBorder(new Color(192, 192, 192), 3));
		setFrameIcon(new ImageIcon(CadastroProduto.class.getResource("/icons/product.png")));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Cadastro de produto");
		setClosable(true);
		setBounds(100, 100, 380, 195);
		getContentPane().setLayout(new MigLayout("", "[grow][][]", "[][][][][][]"));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 0");

		JLabel lblEspaco = new JLabel("    ");
		lblEspaco.setEnabled(false);
		getContentPane().add(lblEspaco, "cell 1 0");

		JLabel lblCodbarras = new JLabel("C\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 2 0");

		// Pre�o

		txtPreco = new JNumberFormatField(2);

		getContentPane().add(txtPreco, "cell 0 5,alignx left");
		txtPreco.setColumns(10);

		// Nome

		txtNome = new JTextField();

		txtNome.setDocument(new JTextFieldLimit(150));

		getContentPane().add(txtNome, "cell 0 1,growx");
		txtNome.setColumns(10);

		// C�digo de barras

		txtCodBar = new JTextField();
		txtCodBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
					arg0.consume();
			}
		});

		txtCodBar.setDocument(new JTextFieldLimit(13));

		getContentPane().add(txtCodBar, "cell 2 1,growx,aligny top");
		txtCodBar.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		getContentPane().add(lblCategoria, "cell 0 2,alignx left");

		JLabel lblEstoque = new JLabel("Estoque:");
		getContentPane().add(lblEstoque, "cell 2 2");

		// Categoria

		consultarCategoria();

		cmbCategoria = new JComboBox();
		cmbCategoria.setBackground(Color.WHITE);
		cmbCategoria.setModel(new DefaultComboBoxModel(listaCategorias.toArray()));
		cmbCategoria.setSelectedIndex(-1);
		getContentPane().add(cmbCategoria, "cell 0 3,growx");

		// Estoque

		txtEstoque = new JTextField();
		txtEstoque.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				char vchar = arg0.getKeyChar();
				if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE))
					arg0.consume();
			}
		});

		getContentPane().add(txtEstoque, "cell 2 3,alignx left");
		txtEstoque.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setIcon(new ImageIcon(CadastroProduto.class.getResource("/icons/check.png")));
		btnSalvar.setPreferredSize(new Dimension(80, 30));
		btnSalvar.setBorder(new LineBorder(Color.gray, 2, true));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setOpaque(true);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Produto produto = new Produto();
					produto.setCodBarra(txtCodBar.getText());
					produto.setNome(txtNome.getText());
					produto.setPreco(Double.parseDouble(txtPreco.getText().replace(",", ".")));
					produto.setEstoque(Integer.parseInt(txtEstoque.getText()));

					Categoria cat = new Categoria();
					cat.setIdCategoria(listaCategorias.get(cmbCategoria.getSelectedIndex()).getIdCategoria());
					cat.setNomeCategoria(listaCategorias.get(cmbCategoria.getSelectedIndex()).getNomeCategoria());
					produto.setCategoria(cat);

					ControllerProduto produtoController = new ControllerProduto();
					produtoController.salvar(produto);
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null, "Verificar se todas as caixas foram preenchidas");
				}
			}
		});

		getContentPane().add(btnSalvar, "cell 2 5,alignx right");

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		getContentPane().add(lblPreco, "flowx,cell 0 4");

	}

	public CadastroProduto(Produto produto) {
		txtNome = new JTextField();
		txtPreco = new JNumberFormatField();
		txtEstoque = new JTextField();
		txtCodBar = new JTextField();
		cmbCategoria = new JComboBox<Categoria>();
		txtCodBar.setText(produto.getCodBarra().toString());
		txtNome.setText(produto.getNome());
		txtPreco.setText("" + produto.getPreco());
		txtEstoque.setText("" + produto.getEstoque());
	}

	private void consultarCategoria() {
		ControllerProduto controllerProduto = new ControllerProduto();
		listaCategorias = controllerProduto.consultarCategoria();
	}

	public void limparCampos() {
		txtNome.setText("");
		txtCodBar.setText("");
		txtPreco.setText("");
		txtEstoque.setText("");
		cmbCategoria.setSelectedIndex(-1);
	}

}
