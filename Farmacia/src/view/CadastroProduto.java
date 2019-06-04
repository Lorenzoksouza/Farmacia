package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ControllerProduto;
import model.vo.Produto;
import net.miginfocom.swing.MigLayout;

public class CadastroProduto extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTextField txtEstoque;
	private JTextField txtCodBar;
	private JComboBox<String> cmbCategoria;

	private ArrayList<String> listaCategorias;

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
		setTitle("Cadastro de Produto");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][]"));

		JLabel lblNome = new JLabel("nome");
		getContentPane().add(lblNome, "cell 0 0");

		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 0 1,growx");
		txtNome.setColumns(10);

		JLabel lblPreco = new JLabel("preco");
		getContentPane().add(lblPreco, "cell 0 2");

		txtPreco = new JTextField();
		getContentPane().add(txtPreco, "cell 0 3,growx");
		txtPreco.setColumns(10);

		JLabel lblCategoria = new JLabel("categoria");
		getContentPane().add(lblCategoria, "cell 0 4,alignx left");

		JLabel lblEstoque = new JLabel("estoque");
		getContentPane().add(lblEstoque, "cell 1 4");

		cmbCategoria = new JComboBox();
		cmbCategoria.setModel(new DefaultComboBoxModel(listaCategorias.toArray()));
		cmbCategoria.setSelectedIndex(-1);
		getContentPane().add(cmbCategoria, "cell 0 5,growx");

		txtEstoque = new JTextField();
		getContentPane().add(txtEstoque, "cell 1 5,growx");
		txtEstoque.setColumns(10);

		JLabel lblCodbarras = new JLabel("cod.barras");
		getContentPane().add(lblCodbarras, "cell 0 6");

		txtCodBar = new JTextField(0);
		getContentPane().add(txtCodBar, "cell 0 7,growx,aligny top");
		txtCodBar.setColumns(10);

		JButton btnSalvar = new JButton("salvar");
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
		getContentPane().add(btnSalvar, "cell 1 7");

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
