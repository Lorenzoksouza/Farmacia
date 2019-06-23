package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class TelaSobre extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setTitle("Sobre");
		setClosable(true);
		setBackground(Color.WHITE);
		setFrameIcon(new ImageIcon(TelaSobre.class.getResource("/icons/network.png")));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
		setBounds(500, 100, 340, 225);
		getContentPane().setLayout(new MigLayout("", "[grow,fill]", "[][][][][][][]"));

		JLabel lblDesenvolvedores = new JLabel("Desenvolvedores:");
		lblDesenvolvedores.setHorizontalAlignment(SwingConstants.CENTER);
		lblDesenvolvedores.setFont(new Font("Tahoma", Font.BOLD, 35));
		getContentPane().add(lblDesenvolvedores, "cell 0 0,alignx center");

		JLabel lblLorenzo = new JLabel("Lorenzo");
		lblLorenzo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblLorenzo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblLorenzo, "cell 0 1,alignx center");

		JLabel lblMatheus = new JLabel("Matheus");
		lblMatheus.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblMatheus.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblMatheus, "cell 0 2,alignx center");

		JLabel lblVitor = new JLabel("Vitor");
		lblVitor.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblVitor.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblVitor, "cell 0 3,alignx center");

		JLabel lblContatoMatheusmartinsalunoscsenacbr = new JLabel("Contato:");
		lblContatoMatheusmartinsalunoscsenacbr.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContatoMatheusmartinsalunoscsenacbr.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(lblContatoMatheusmartinsalunoscsenacbr, "flowx,cell 0 4,growx");

		JTextArea txtrMatheusmartinsalunoscsenacbr = new JTextArea();
		txtrMatheusmartinsalunoscsenacbr.setEditable(false);
		txtrMatheusmartinsalunoscsenacbr.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtrMatheusmartinsalunoscsenacbr.setText("matheus.martins@aluno.sc.senac.br");
		getContentPane().add(txtrMatheusmartinsalunoscsenacbr, "cell 0 4,growx");

		JLabel lblV = new JLabel("V.1.0");
		getContentPane().add(lblV, "flowx,cell 0 5");

		JLabel label = new JLabel("\u00A9 2019");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(label, "cell 0 5");
		String name = UIManager.getSystemLookAndFeelClassName();

	}

}
