package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

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
		setBounds(100, 100, 450, 300);

	}

}
