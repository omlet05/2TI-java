package defautPackage;

import java.awt.Container;
import java.sql.Connection;

import javax.swing.JFrame;

public class UpdateFrame extends JFrame {
	private Container cont;
	private UpdatePane panel;
	private MainFrame MyMainFrame;
	private Connection conn;

	public UpdateFrame(MainFrame f) {
		setTitle("Modification d'une Installation.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 486);
		setLocationRelativeTo(null);
		setResizable(false);
		
		MyMainFrame = f;
		setConn(f.getConn());

		panel = new UpdatePane(UpdateFrame.this); 

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
