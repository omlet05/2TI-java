package defautPackage;

import java.awt.Container;
import java.sql.Connection;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Search1Frame extends JFrame {
	private Container cont;
	private Search1Pane panel;
	private Connection conn;
	private MainFrame mainFrame;
	public Search1Frame(MainFrame main){
		setTitle("Software sans code d'installation, en fonction d'une année scolaire.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new javax.swing.ImageIcon("res/icons/listing-icon.png").getImage());
		setSize(1000, 550);
		setResizable(false);
		setLocationRelativeTo(null);

		mainFrame = main;
		setConn(mainFrame.getConn());
		
		panel = new Search1Pane(Search1Frame.this);

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
