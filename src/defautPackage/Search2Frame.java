package defautPackage;

import java.awt.Container;
import java.sql.Connection;

import javax.swing.JFrame;

public class Search2Frame extends JFrame {
	private Container cont;
	private Search2Pane panel;
	private Connection conn;
	@SuppressWarnings("unused")
	private MainFrame mainFrame;
	public Search2Frame(MainFrame main){
		setTitle("Recherche n°2");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 550);
		setResizable(false);
		setLocationRelativeTo(null);
		
		mainFrame = main;
		setConn(mainFrame.getConn());

		panel= new Search2Pane(Search2Frame.this);

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
