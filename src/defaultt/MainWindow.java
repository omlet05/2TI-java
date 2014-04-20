package defaultt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.Border;





public class MainWindow extends JFrame{
	private Container cont;
	private JLabel motd;
	private JMenuBar barMenu;
	private JMenu menuDb, menuEncode, menuListing, menuExit;
	private static JMenuItem menuItemLogin;
	private static JMenuItem menuItemLogout;
	private static JMenuItem menuItemAdd;
	private static JMenuItem menuItemDel;
	private static JMenuItem menuItemSearch1;
	private static JMenuItem menuItemSearch2;
	private JMenuItem menuItemExit;
	private static StatusBar bar;
	public static Connection conn = null;
	private JPanel panel;
	private JMenuItem mntmLookChange;
	
	
	
		
	
	public MainWindow() {
        super("Java 2014 Serie U LM-KG 2TIc: Fenêtre Principale.");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        //icon
        Image icon = new javax.swing.ImageIcon("res/icon.png").getImage();
        this.setIconImage(icon);
        
        
        
        //container
        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        
        //JMenuBar
        
        barMenu = new JMenuBar();
        
        menuDb = new JMenu("Base de données");
        menuEncode = new JMenu("Encoder");
        menuListing = new JMenu("Listing");
        menuExit = new JMenu("Quitter");
        
        
        menuItemLogin = new JMenuItem("Connexion");
		menuItemLogout = new JMenuItem("Déconnexion");
		
		menuItemAdd = new JMenuItem("Ajout");
		menuItemDel = new JMenuItem("Supression");
		
		menuItemSearch1 = new JMenuItem("Software sans code d'installation, en fonction d'une année scolaire.");
		menuItemSearch2 = new JMenuItem("Software après une date et par un responsable réseau particulier.");
		
		menuItemExit = new JMenuItem("Quitter");
		
		
		menuDb.add(menuItemLogin);
		menuDb.add(menuItemLogout);
		
		menuEncode.add(menuItemAdd);
		menuEncode.add(menuItemDel);
		
		menuListing.add(menuItemSearch1);
		menuListing.add(menuItemSearch2);
		
		menuExit.add(menuItemExit);
		
		barMenu.add(menuDb);
        barMenu.add(menuEncode);
        barMenu.add(menuListing);
        barMenu.add(menuExit);
        
        
        
        //menu actions
        
        menuItemLogin.addActionListener(new Login());
        menuItemLogout.addActionListener(new Logout());
        menuItemAdd.addActionListener(new Add());
        menuItemDel.addActionListener(new Del());

        
        menuItemExit.addActionListener(new Exit());
        
        //welcome message
        motd = new JLabel("<html><br><br><br><br><b>&nbsp;&nbsp;&nbsp;&nbsp;<u>Bienvenue</u></b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<li><ul>Ce programme...</ul><ul>Ce programme...</ul><ul>Ce programme...</ul></li></html>", SwingConstants.LEFT);
        motd.setVerticalAlignment(SwingConstants.TOP);
        getContentPane().add(motd, BorderLayout.CENTER);
        motd.setPreferredSize(new Dimension(0, 150));
            
        //statusBar
        bar  = new StatusBar();
        getContentPane().add(bar, java.awt.BorderLayout.SOUTH);
        
        panel = new JPanel();
        getContentPane().add(panel, BorderLayout.NORTH);
        setBarStat(false);
        
        //setup the JMenuBar
        setJMenuBar(barMenu);
        
        
        //set visible the window
        setVisible(true);


	}
	
	public static void setBarStat(Boolean boo){
		String txt = null; 
		if(!boo){
			//disable who require login
			menuItemLogout.setEnabled(false);
			menuItemAdd.setEnabled(false);
			menuItemDel.setEnabled(false);
			menuItemSearch1.setEnabled(false);
			menuItemSearch2.setEnabled(false);
			//enable needed
			menuItemLogin.setEnabled(true);
			txt = " Status connexion: off";
			bar.setForeground(Color.red);
		}
		else{
			//same
			menuItemLogout.setEnabled(true);
			menuItemAdd.setEnabled(true);
			menuItemDel.setEnabled(true);
			menuItemSearch1.setEnabled(true);
			menuItemSearch2.setEnabled(true);
			//same
			menuItemLogin.setEnabled(false);
			txt = " Status connexion: on";
			//Hexa color
			Color aColor = new Color(0x009900);
			bar.setForeground(aColor);
		}
		bar.setText(txt);
		
		//border
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		bar.setBorder(border);
		
	}
    
	private class Login implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			new LoginWindow();
 			
 		}
 	}
	
	private class Add implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new AddWindow();
			
		}
	}
	
	private class Del implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			new DelWindow();
 			
 		}
 	}

	private class Logout implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			try {
				if(conn != null){
					AccesBDGen.deconnecter(conn);
					conn = null;
					setBarStat(false);
				}
			}
 			catch (Exception e1) {
 				System.out.println("Erreur lors de la déconnexion : "+e1.getMessage());
			}
 		}
 	}
	
	private class Exit implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			System.exit(0);
 		}
 	}
	
	

	
	
	
}
