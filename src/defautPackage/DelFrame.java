package defautPackage;

import java.awt.Container;
import java.sql.Connection;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class DelFrame extends JFrame {

	private Container cont;
	private DelPane panel;
	private Connection conn;
	@SuppressWarnings("unused")
	private MainFrame mainFrame;

	public DelFrame(MainFrame mainFrame) {
		setTitle("Supression d'un software.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(792, 526);
		setResizable(false);
		setLocationRelativeTo(null);

		setConn(mainFrame.getConn());

		panel = new DelPane(DelFrame.this);

		cont = getContentPane();
		cont.setLayout(null);
		cont.add(panel);

		setVisible(true);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
