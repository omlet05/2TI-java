package defautPackage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import AccesBD.AccesBDGen;

@SuppressWarnings("serial")
public class LoginPane extends JPanel {
	private JLabel dbLabel, loginLabel, passwordLabel;
	private JTextField dbField, loginField;
	private JPasswordField passwordField;
	private JButton buttonConnexion, buttonBack;
	@SuppressWarnings("rawtypes")
	private JComboBox typedb;
	private LoginFrame myParentFrame;
	private MainFrame myParentMainFrame;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LoginPane(MainFrame mainF, LoginFrame f) {
		myParentFrame = f;
		myParentMainFrame = mainF;

		setBounds(10, 10, 220, 160);
		setLayout(new GridLayout(5, 2));

		// Form

		dbLabel = new JLabel(" Table: ");
		add(dbLabel);
		dbField = new JTextField("test");
		add(dbField);
		loginLabel = new JLabel(" Login: ");
		add(loginLabel);
		loginField = new JTextField("root");
		add(loginField);

		passwordLabel = new JLabel(" Mot de passe: ");
		add(passwordLabel);
		passwordField = new JPasswordField("");
		add(passwordField);

		String[] type = { "mysql", "odbc" };
		typedb = new JComboBox(type);
		typedb.setEnabled(false);
		add(new JLabel(" Type BD:"));
		add(typedb);

		buttonConnexion = new JButton("Connexion");
		add(buttonConnexion);

		buttonBack = new JButton("Retour");
		add(buttonBack);

		buttonConnexion.addActionListener(new Connexion());
		buttonBack.addActionListener(new Exit());

		// default button allow the user to press enter to get connection.

		myParentFrame.getRootPane().setDefaultButton(buttonConnexion);

		// choose the JpasswordField
		myParentFrame.addWindowFocusListener(new FocusPass());

	}

	private class FocusPass implements WindowFocusListener {
		public void windowGainedFocus(WindowEvent e) {
			passwordField.requestFocusInWindow();
		}

		public void windowLostFocus(WindowEvent e) {
			// nothing

		}
	}

	private class Connexion implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Connection conTMP = null;
				String passwordString = new String(passwordField.getPassword());

				conTMP = AccesBDGen.connecter(typedb.getSelectedItem().toString(), dbField.getText(), loginField.getText(),passwordString);
				myParentMainFrame.setConn(conTMP);
				myParentMainFrame.setBarStat(true);
				myParentFrame.dispose();
			} 
			catch (SQLException sqle){
				int errorCode = sqle.getErrorCode();
				if(errorCode == 0)
					JOptionPane.showMessageDialog(null,"Pas de connexion à la base de données, vérifiez la connectivité avec le serveur.","Erreur de connectivité!", JOptionPane.ERROR_MESSAGE);
				else if(errorCode == 1045)
					JOptionPane.showMessageDialog(null,"Vérifiez vos identifiants de connexions.\n"+ sqle.getMessage(),"Connexion impossible!", JOptionPane.ERROR_MESSAGE);
				else if(errorCode == 1049)
					JOptionPane.showMessageDialog(null,"La table introduite n'existe pas.\n"+ sqle.getMessage(),"Mauvaise table!", JOptionPane.ERROR_MESSAGE);
				else{
					JOptionPane.showMessageDialog(null,"Erreur lors de la connexion : "+ sqle.getMessage(),"Erreur mysql!", JOptionPane.ERROR_MESSAGE);
					System.out.println("MYSQL ErrorCode = "+errorCode);
				}
				
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(null,"Erreur lors de la connexion : " + e1.getMessage(),"Erreur!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private class Exit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myParentFrame.dispose();
		}
	}

}
