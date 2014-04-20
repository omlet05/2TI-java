package defaultt;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;




public class LoginWindow extends JFrame{
	private Container cont;
	private JPanel mainPanel;
	private JLabel dbLabel, loginLabel, passwordLabel;
	private JTextField dbField, loginField;
	private JPasswordField passwordField;
	private JButton buttonConnexion, buttonBack;
	private JComboBox typedb;
	
	
	public LoginWindow(){
		super("Connexion");
		setSize(223, 161);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
		
		
		//container
		cont = getContentPane();
		cont.setLayout(new BorderLayout());
		
		//main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5,2));
		cont.add(mainPanel);
		
		
		
		//Form	
		
		dbLabel = new JLabel(" Bdd: ");
		mainPanel.add(dbLabel);
		dbField = new JTextField("test");
		mainPanel.add(dbField);
		loginLabel = new JLabel(" Login: ");
		mainPanel.add(loginLabel);
		loginField = new JTextField("root");
		mainPanel.add(loginField);

		
		passwordLabel = new JLabel(" Mot de passe: ");
		mainPanel.add(passwordLabel);
		passwordField = new JPasswordField("");
		mainPanel.add(passwordField);
		

		String[] type = {"mysql", "odbc"};
		typedb = new JComboBox(type);
		typedb.setEnabled(false);
		mainPanel.add(new JLabel(" Type BD:"));
		mainPanel.add(typedb);
		
		buttonConnexion = new JButton("Connexion");
		mainPanel.add(buttonConnexion);
		
		
		buttonBack = new JButton("Retour");
		mainPanel.add(buttonBack);
		
		buttonConnexion.addActionListener(new Connexion());
		buttonBack.addActionListener(new Exit());
		setVisible(true);
		
	}	
	private class Connexion implements ActionListener {
  	 	public void actionPerformed(ActionEvent e){
  	 		try{
	  	 		String passwordString=new String(passwordField.getPassword());
	  	 		MainWindow.conn = AccesBDGen.connecter(typedb.getSelectedItem().toString(), dbField.getText(), loginField.getText(), passwordString);
	  	 		MainWindow.setBarStat(true);
	  	 		dispose();
  	 		}
  	 		catch (Exception e1){
  	 			JOptionPane.showMessageDialog(null,"Erreur lors de la connexion : "+e1.getMessage(),"Erreur!",JOptionPane.ERROR_MESSAGE);
  	 		}
  	 	}
  	 }
	private class Exit implements ActionListener{
  	 	public void actionPerformed(ActionEvent e){
  	 		dispose();
  	 	}
  	 }
	
}