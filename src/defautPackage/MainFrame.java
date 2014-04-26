package defautPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.Border;

import AccesBD.AccesBDGen;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static Container cont;
	public static MainPane mainPane;
	public static AproposPane proposPane;
	private JMenuBar barMenu;
	private JMenu menuDb, MenuInstal, menuListing, menuExit;
	private static JMenuItem menuItemLogin, menuItemLogout, menuItemAdd,
			menuItemDel, menuItemSearch1, menuItemSearch2, menuItemAPropos;
	private static StatusBar bar;
	private Connection conn = null;
	private Image icon;

	public MainFrame() {
		super("Java 2014 Serie U LM-KG 2TIc: Fenêtre Principale.");
		setSize(640, 480);
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
		menuItemLogout = new JMenuItem("Déconnexion");

		menuItemAdd = new JMenuItem("Ajout");
		menuItemDel = new JMenuItem("Supression");

		menuItemSearch1 = new JMenuItem(
				"Software sans code d'installation, en fonction d'une année scolaire.");
		menuItemSearch2 = new JMenuItem(
				"Software après une date et par un responsable réseau particulier.");

		menuItemAPropos = new JMenuItem("A Propos");

		menuDb.add(menuItemLogin);
		menuDb.add(menuItemLogout);

		MenuInstal.add(menuItemAdd);
		MenuInstal.add(menuItemDel);

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
		menuItemAPropos.addActionListener(new Apropos());

		// statusBar
		bar = new StatusBar();
		getContentPane().add(bar, java.awt.BorderLayout.SOUTH);
		setBarStat(false);

		// setup the JMenuBar
		setJMenuBar(barMenu);

		// mainPanel
		mainPane = new MainPane();
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
			menuItemSearch1.setEnabled(false);
			menuItemSearch2.setEnabled(false);
			// enable needed
			menuItemLogin.setEnabled(true);
			txt = " Status connexion: off";
			bar.setForeground(Color.red);
		} else {
			// same
			menuItemLogout.setEnabled(true);
			menuItemAdd.setEnabled(true);
			menuItemDel.setEnabled(true);
			menuItemSearch1.setEnabled(true);
			menuItemSearch2.setEnabled(true);
			// same
			menuItemLogin.setEnabled(false);
			txt = " Status connexion: on";
			// Hexa color
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
				System.out.println("Erreur lors de la déconnexion : "
						+ e1.getMessage());
			}
		}
	}

	private class Apropos implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			proposPane = new AproposPane(MainFrame.this);

			cont.remove(mainPane);
			cont.add(proposPane, BorderLayout.CENTER);
			cont.repaint();
		}
	}

	public void redrawNewMain() {
		mainPane = new MainPane();
		cont.remove(proposPane);
		cont.add(mainPane, BorderLayout.CENTER);
		cont.repaint();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
