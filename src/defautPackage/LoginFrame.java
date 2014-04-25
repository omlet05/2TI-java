package defautPackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import AccesBD.AccesBDGen;




public class LoginFrame extends JFrame{
	private Container cont;
	private LoginPane panel;
	
	
	
	public LoginFrame(){
		super("Connexion");
		setSize(250, 250);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        panel = new LoginPane();
		
		
		//container
		cont = getContentPane();
		cont.setLayout(null); 
		cont.add (panel);
		
		
		
		setVisible(true);
	}
}
		
