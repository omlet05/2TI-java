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

import AccesBD.AccesBDGen;





public class MainFrame extends JFrame{
	private static Container cont;
	public static MainPane mainPane;
	public static AproposPane proposPane;
	private JLabel motd;
	private JMenuBar barMenu;
	private JMenu menuDb, MenuInstal, menuListing, menuExit;
	private static JMenuItem menuItemLogin, menuItemLogout, menuItemAdd, menuItemDel, menuItemSearch1, menuItemSearch2, menuItemAPropos, menuItemExit;
	private static StatusBar bar;
	public static Connection conn = null;
	private Image icon;
	
	
		
	
	public MainFrame() {
        super("Java 2014 Serie U LM-KG 2TIc: Fenêtre Principale.");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        //icon
        icon = new javax.swing.ImageIcon("res/icon.png").getImage();
        this.setIconImage(icon);
        
        
              
        //container
        cont = getContentPane();
        cont.setLayout(new BorderLayout());
        
        //JMenuBar
        
        barMenu = new JMenuBar();
        
        menuDb = new JMenu("Base de données");
        MenuInstal = new JMenu("Installation");
        menuListing = new JMenu("Listing");
        menuExit = new JMenu("Application");
        
        
        menuItemLogin = new JMenuItem("Connexion");
		menuItemLogout = new JMenuItem("Déconnexion");
		
		menuItemAdd = new JMenuItem("Ajout");
		menuItemDel = new JMenuItem("Supression");
		
		menuItemSearch1 = new JMenuItem("Software sans code d'installation, en fonction d'une année scolaire.");
		menuItemSearch2 = new JMenuItem("Software après une date et par un responsable réseau particulier.");
		
		menuItemAPropos = new JMenuItem("A Propos");
		menuItemExit = new JMenuItem("Quitter");
		
		
		menuDb.add(menuItemLogin);
		menuDb.add(menuItemLogout);
		
		MenuInstal.add(menuItemAdd);
		MenuInstal.add(menuItemDel);
		
		menuListing.add(menuItemSearch1);
		menuListing.add(menuItemSearch2);
		
		menuExit.add(menuItemAPropos);
		menuExit.add(menuItemExit);
		
		barMenu.add(menuDb);
        barMenu.add(MenuInstal);
        barMenu.add(menuListing);
        barMenu.add(menuExit);
        
        
        
        //menu actions
        
        menuItemLogin.addActionListener(new Login());
        menuItemLogout.addActionListener(new Logout());
        menuItemAdd.addActionListener(new Add());
        menuItemDel.addActionListener(new Del());
        menuItemAPropos.addActionListener(new Apropos());
        menuItemExit.addActionListener(new Exit());
        

            
        //statusBar
        bar  = new StatusBar();
        getContentPane().add(bar, java.awt.BorderLayout.SOUTH);
        setBarStat(false);
        
        //setup the JMenuBar
        setJMenuBar(barMenu);
        
        
        //mainPanel
        mainPane = new MainPane();
        proposPane = new AproposPane();
        getContentPane().add(mainPane, BorderLayout.CENTER);
        
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
 			new LoginFrame();
 			
 		}
 	}
	
	private class Add implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new AddFrame();
			
		}
	}
	
	private class Del implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			new DelFrame();
 			
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
	
	private class Apropos implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			proposPane = new AproposPane();
 			
 			cont.remove(mainPane);
 			cont.add(proposPane, BorderLayout.CENTER);
 			cont.repaint();
 		}
 	}
	
	private class Exit implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			System.exit(0);
 		}
 	}
	
	
	public static void redrawNewMain(){
 			mainPane = new MainPane();
 			
 			cont.remove(proposPane);
 			cont.add(mainPane, BorderLayout.CENTER);
 			cont.repaint();
 			System.out.println("test");
 	}
}
