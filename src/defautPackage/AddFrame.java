package defautPackage;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import AccesBD.AccesBDGen;

import java.sql.*;
import java.text.SimpleDateFormat;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;






import java.util.Date;
import java.awt.Color;

public class AddFrame extends JFrame {
	private Container cont;
	private AddPane panel;
	private Connection conn;
	private MainFrame mainFrame;


	public AddFrame(MainFrame main) {
		setTitle("Ajouter une installation.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 450);
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
