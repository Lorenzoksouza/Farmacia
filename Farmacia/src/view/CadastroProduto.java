package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

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
		setTitle("Cadastro de produto");
		setClosable(true);
		setBounds(100, 100, 380, 195);
		getContentPane().setLayout(new MigLayout("", "[grow][][]", "[][][][][][]"));

		JLabel lblNome = new JLabel("Nome:");
		getContentPane().add(lblNome, "cell 0 0");

		JLabel lblEspaco = new JLabel("    ");
		lblEspaco.setEnabled(false);
		getContentPane().add(lblEspaco, "cell 1 0");

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		getContentPane().add(lblPreco, "cell 2 0");

		txtNome = new JFormattedTextField();

		MaskFormatter formatonome = new MaskFormatter();

		try {
			formatonome = new MaskFormatter("************************************************************");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatonome.setValidCharacters(
				"a����bcde����fghi����jklmno���pqrstu����vwxyz-()/:A����BCDE����FGHI����JKLMNO���PQRSTU����VWXYZ");

		formatonome.install((JFormattedTextField) txtNome);

		getContentPane().add(txtNome, "cell 0 1,growx");
		txtNome.setColumns(10);

		txtPreco = new JFormattedTextField();

		MaskFormatter formatoPreco = new MaskFormatter();

		try {
			formatoPreco = new MaskFormatter("R$" + "####,##");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoPreco.setValidCharacters("0123456789");

		formatoPreco.install((JFormattedTextField) txtPreco);

		getContentPane().add(txtPreco, "cell 2 1,alignx left");
		txtPreco.setColumns(10);

		JLabel lblCategoria = new JLabel("Categoria:");
		getContentPane().add(lblCategoria, "cell 0 2,alignx left");

		JLabel lblEstoque = new JLabel("Estoque:");
		getContentPane().add(lblEstoque, "cell 2 2");

		consultarCategoria();

		cmbCategoria = new JComboBox();
		cmbCategoria.setBackground(Color.WHITE);
		cmbCategoria.setModel(new DefaultComboBoxModel(listaCategorias.toArray()));
		cmbCategoria.setSelectedIndex(-1);
		getContentPane().add(cmbCategoria, "cell 0 3,growx");

		txtEstoque = new JFormattedTextField();

		MaskFormatter formatoEstoque = new MaskFormatter();

		try {
			formatoEstoque = new MaskFormatter("###");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoEstoque.setValidCharacters("0123456789");

		formatoEstoque.install((JFormattedTextField) txtEstoque);

		getContentPane().add(txtEstoque, "cell 2 3,alignx left");
		txtEstoque.setColumns(10);

		JLabel lblCodbarras = new JLabel("C\u00F3d.barras:");
		getContentPane().add(lblCodbarras, "cell 0 4");

		txtCodBar = new JFormattedTextField();

		MaskFormatter formatoCodBar = new MaskFormatter();

		try {
			formatoCodBar = new MaskFormatter("################");
		} catch (ParseException e1) { // TODO Auto-generated catch block
			e1.printStackTrace();
		}

		formatoCodBar.setValidCharacters("0123456789");

		formatoCodBar.install((JFormattedTextField) txtCodBar);

		getContentPane().add(txtCodBar, "cell 0 5,growx,aligny top");
		txtCodBar.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
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
	}

	public void limparCampos() {
		txtNome.setText("");
		txtCodBar.setText("");
		txtPreco.setText("");
		txtEstoque.setText("");
		cmbCategoria.setSelectedIndex(-1);
	}

}
