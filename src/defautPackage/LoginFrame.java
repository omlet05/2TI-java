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
	private MainFrame mainF;
	
	
	
	public LoginFrame(MainFrame f){
		super("Connexion");
		setSize(258, 215);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        mainF = f;
        
        panel = new LoginPane(mainF, LoginFrame.this);
        panel.setLocation(10, 11);
		
		
		//container
		cont = getContentPane();
		cont.setLayout(null); 
		cont.add (panel);
		
		
		
		setVisible(true);
	}
}
		
