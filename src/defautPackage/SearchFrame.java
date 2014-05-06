package defautPackage;

import java.awt.Container;
import java.sql.Connection;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SearchFrame extends JFrame {
	private Container cont;
	private Search2Pane panel2;
	private Search1Pane panel1;
	private Connection conn;
	private MainFrame mainFrame;
	public SearchFrame(MainFrame main, int i){
		setTitle("Software sans code d'installation, en fonction d'une année scolaire.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new javax.swing.ImageIcon("res/icons/listing-icon.png").getImage());
		setSize(1000, 550);
		setResizable(false);
		setLocationRelativeTo(null);

		mainFrame = main;
		setConn(mainFrame.getConn());

		cont = getContentPane();
		cont.setLayout(null);
		if (i==1){
			panel1 = new Search1Pane(SearchFrame.this);
			cont.add(panel1);
		}
		else{
			panel2 = new Search2Pane(SearchFrame.this);
			cont.add(panel2);
		}

		setVisible(true);
	}
	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
