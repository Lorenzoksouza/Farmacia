package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import controller.ControllerProduto;
import model.vo.Categoria;
import model.vo.Produto;
import net.miginfocom.swing.MigLayout;

public class CadastroProduto extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtPreco;
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
		setFrameIcon(new ImageIcon(CadastroProduto.class.getResource("/icons/prod3x.png")));
		getContentPane().setBackground(Color.WHITE);
		setTitle("Cadastro de Produto");
		setClosable(true);
		setBounds(100, 100, 380, 195);
		getContentPane().setLayout(new MigLayout("", "[grow][][]", "[][][][][][]"));

		JLabel lblNome = new JLabel("nome:");
		getContentPane().add(lblNome, "cell 0 0");

		JLabel lblEspaco = new JLabel("    ");
		lblEspaco.setEnabled(false);
		getContentPane().add(lblEspaco, "cell 1 0");

		JLabel lblPreco = new JLabel("preco:");
		getContentPane().add(lblPreco, "cell 2 0");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 1,growx");
		txtNome.setColumns(10);

		txtPreco = new JTextField();
		getContentPane().add(txtPreco, "cell 2 1,alignx left");
		txtPreco.setColumns(10);

		JLabel lblCategoria = new JLabel("categoria:");
		getContentPane().add(lblCategoria, "cell 0 2,alignx left");

		JLabel lblEstoque = new JLabel("estoque:");
		getContentPane().add(lblEstoque, "cell 2 2");

		consultarCategoria();

		cmbCategoria = new JComboBox();
		cmbCategoria.setBackground(Color.WHITE);
		cmbCategoria.setModel(new DefaultComboBoxModel(listaCategorias.toArray()));
		cmbCategoria.setSelectedIndex(-1);
		getContentPane().add(cmbCategoria, "cell 0 3,growx");

		txtEstoque = new JTextField();
		getContentPane().add(txtEstoque, "cell 2 3,alignx left");
		txtEstoque.setColumns(10);

		JLabel lblCodbarras = new JLabel("c\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 0 4");

		txtCodBar = new JTextField(0);
		getContentPane().add(txtCodBar, "cell 0 5,growx,aligny top");
		txtCodBar.setColumns(10);

		JButton btnSalvar = new JButton("salvar");
		btnSalvar.setPreferredSize(new Dimension(80, 30));
		btnSalvar.setBorder(new LineBorder(Color.gray, 2, true));
		btnSalvar.setBackground(Color.WHITE);
		btnSalvar.setOpaque(true);
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto produto = new Produto();
				produto.setCodBarra(txtCodBar.getText());
				produto.setNome(txtNome.getText());
				produto.setPreco(Double.parseDouble(txtPreco.getText()));
				produto.setEstoque(Integer.parseInt(txtEstoque.getText()));
				produto.setCategoria(cmbCategoria.getSelectedItem().toString());

				ControllerProduto produtoController = new ControllerProduto();
				produtoController.salvar(produto);
			}
		});
		getContentPane().add(btnSalvar, "cell 2 5,alignx right");

	}

	public CadastroProduto(Produto produto) {
		txtCodBar.setText(produto.getCodBarra().toString());
		txtNome.setText(produto.getNome());
		txtPreco.setText("" + produto.getPreco());
		txtEstoque.setText("" + produto.getEstoque());

	}

	private void consultarCategoria() {
		ControllerProduto controllerProduto = new ControllerProduto();
		listaCategorias = controllerProduto.consultarCategoria();

		// fazer retornar listaLaboratorios do RemedioDAO!
		// após isto, descomentar linha 96
	}

}
