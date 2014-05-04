package defautPackage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainPane extends JPanel {
	private JLabel motd;
	private JPanel contentP;
	private MainFrame myParentMainFrame;

	public MainPane(MainFrame mF) {
		myParentMainFrame = mF;
		this.setBounds(10, 10, 597, 335);
		setLayout(null);

		// welcome message
		motd = new JLabel(
				"<html><br><br><br><br><b>&nbsp;&nbsp;&nbsp;&nbsp;<u>Bienvenue sur l'application Java de <i>Kevin Gouverneur</i> et de <i>Mathieu Lobet</i> 2Tic</u></b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<li><ul>- Ce programme permet une connexion, déconnexion de la base de données via le menu <b>\"Base de données\"</b>.</ul><ul> - Un ajout, suppression et modification d'une installation de Software via le menu <b>\"Installation.\"</b></ul><ul>- Le listing de différents enregistrements selon certains critères via le menu <b>\"Listing\"</b>.</ul><ul>- Diverses informations à propos du projet via le menu <b>\"Application\".</b></ul></li></html>",
				SwingConstants.LEFT);
		motd.setBounds(0, 0, 597, 338);
		motd.setVerticalAlignment(SwingConstants.TOP);

		add(motd);
	}
	
}