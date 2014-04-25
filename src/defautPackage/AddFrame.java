package defautPackage;


import java.awt.Container;
import javax.swing.*;
import java.sql.*;


@SuppressWarnings("serial")
public class AddFrame extends JFrame {
	private Container cont;
	private AddPane panel;
	private Connection conn;
	private MainFrame mainFrame;


	public AddFrame(MainFrame main) {
		setTitle("Ajouter une installation.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 486);
		setLocationRelativeTo(null);
		setResizable(false);
		
		mainFrame = main;
		setConn(mainFrame.getConn());
		
		panel = new AddPane(AddFrame.this); //passer fenêtre 
		
		
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
