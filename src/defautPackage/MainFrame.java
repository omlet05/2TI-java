package defautPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

import AccesBD.AccesBDGen;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static Search1Pane search1;
	public static Search2Pane search2;
	private static Container cont;
	public static MainPane mainPane;
	public static AproposPane proposPane;
	private JMenuBar barMenu;
	private JMenu menuDb, MenuInstal, menuListing, menuExit;
	private static JMenuItem menuItemLogin, menuItemLogout, menuItemAdd,
			menuItemDel, menuItemUpdate, menuItemSearch1, menuItemSearch2, menuItemAPropos;
	private static StatusBar bar;
	private Connection conn = null;
	private Image icon;
	

	public MainFrame() {
		super("Java 2014 série U LM-KG 2TIc: Fenêtre principale.");
		setSize(640, 494);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// icon
		icon = new javax.swing.ImageIcon("res/icon.png").getImage();
		this.setIconImage(icon);

		// container
		cont = getContentPane();
		cont.setLayout(new BorderLayout());

		// JMenuBar

		barMenu = new JMenuBar();

		menuDb = new JMenu("Base de données");
		MenuInstal = new JMenu("Installation");
		menuListing = new JMenu("Listing");
		menuExit = new JMenu("Application");

		menuItemLogin = new JMenuItem("Connexion");
		menuItemLogin.setIcon(new ImageIcon("res/icons/login-icon.png"));
		menuItemLogout = new JMenuItem("Déconnexion");
		menuItemLogout.setIcon(new ImageIcon("res/icons/logout-icon.png"));

		menuItemAdd = new JMenuItem("Ajout");
		menuItemAdd.setIcon(new ImageIcon("res/icons/add-icon.png"));
		menuItemDel = new JMenuItem("Suppression");
		menuItemDel.setIcon(new ImageIcon("res/icons/remove-icon.png"));
		menuItemUpdate = new JMenuItem("Modification");
		menuItemUpdate.setIcon(new ImageIcon("res/icons/edit-icon.png"));

		menuItemSearch1 = new JMenuItem("Logiciels sans code d'installation, en fonction d'une année scolaire.");
		menuItemSearch2 = new JMenuItem("Installations après une date et conduites par un responsable réseau particulier.");
		menuItemSearch1.setIcon(new ImageIcon("res/icons/listing-icon.png"));
		menuItemSearch2.setIcon(new ImageIcon("res/icons/listing-icon.png"));

		menuItemAPropos = new JMenuItem("A Propos");
		menuItemAPropos.setIcon(new ImageIcon("res/icons/about-icon.png"));

		menuDb.add(menuItemLogin);
		menuDb.add(menuItemLogout);

		MenuInstal.add(menuItemAdd);
		MenuInstal.add(menuItemDel);
		MenuInstal.add(menuItemUpdate);

		menuListing.add(menuItemSearch1);
		menuListing.add(menuItemSearch2);

		menuExit.add(menuItemAPropos);

		barMenu.add(menuDb);
		barMenu.add(MenuInstal);
		barMenu.add(menuListing);
		barMenu.add(menuExit);

		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new Exit());
		barMenu.add(btnExit);

		// menu actions

		menuItemLogin.addActionListener(new Login());
		menuItemLogout.addActionListener(new Logout());
		menuItemAdd.addActionListener(new Add());
		menuItemDel.addActionListener(new Del());
		menuItemUpdate.addActionListener(new Update());
		menuItemAPropos.addActionListener(new Apropos());
		menuItemSearch1.addActionListener(new Search1());
		menuItemSearch2.addActionListener(new Search2());
		

		// statusBar
		bar = new StatusBar();
		getContentPane().add(bar, java.awt.BorderLayout.SOUTH);
		setBarStat(false);

		// setup the JMenuBar
		setJMenuBar(barMenu);

		// mainPanel
		mainPane = new MainPane(MainFrame.this);
		proposPane = new AproposPane(MainFrame.this);
		getContentPane().add(mainPane, BorderLayout.CENTER);

		// set visible the window
		setVisible(true);

	}

	public void setBarStat(Boolean boo) {
		String txt = null;
		if (!boo) {
			// disable who require login
			menuItemLogout.setEnabled(false);
			menuItemAdd.setEnabled(false);
			menuItemDel.setEnabled(false);
			menuItemUpdate.setEnabled(false);
			menuItemSearch1.setEnabled(false);
			menuItemSearch2.setEnabled(false);
			// enable needed
			menuItemLogin.setEnabled(true);
			txt = " État de la connexion: non établie";
			bar.setForeground(Color.red);
		} else {
			// same
			menuItemLogout.setEnabled(true);
			menuItemAdd.setEnabled(true);
			menuItemDel.setEnabled(true);
			menuItemUpdate.setEnabled(true);
			menuItemSearch1.setEnabled(true);
			menuItemSearch2.setEnabled(true);
			// same
			menuItemLogin.setEnabled(false);
			txt = " État de la connexion: établie";
			// Hexa colorUpdateFrame
			Color aColor = new Color(0x009900);
			bar.setForeground(aColor);
		}
		bar.setText(txt);

		// border
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		bar.setBorder(border);

	}

	private class Login implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new LoginFrame(MainFrame.this);

		}
	}

	private class Add implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new AddFrame(MainFrame.this);

		}
	}

	private class Del implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new DelFrame(MainFrame.this);
			
		}
	}
	
	private class Update implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new UpdateFrame(MainFrame.this);

		}
	}

	private class Exit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}
	}

	private class Logout implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				if (conn != null) {
					AccesBDGen.deconnecter(conn);
					conn = null;
					setBarStat(false);
				}
			} catch (Exception e1) {
				System.out.println("Erreur lors de la déconnexion : "+ e1.getMessage());
			}
		}
	}

	private class Apropos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			proposPane = new AproposPane(MainFrame.this);
			cont.remove(mainPane);
			cont.add(proposPane, BorderLayout.CENTER);
			cont.repaint();
			menuItemAPropos.setEnabled(false);
		}
	}
	
	private class Search1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new SearchFrame(MainFrame.this, 1);
		}
	}

	private class Search2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			new SearchFrame(MainFrame.this, 2);
		}
	}
	public void redrawNewMain() {
		mainPane = new MainPane(MainFrame.this);
		cont.remove(proposPane);
		cont.add(mainPane, BorderLayout.CENTER);
		cont.repaint();
		menuItemAPropos.setEnabled(true);
	}
	
	public void setLoginMenuItem(Boolean bool){
		if(bool)
			menuItemLogin.setEnabled(true);
		else
			menuItemLogin.setEnabled(false);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
