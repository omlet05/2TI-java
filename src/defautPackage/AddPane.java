package defautPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import AccesBD.AccesBDGen;

@SuppressWarnings("serial")
public class AddPane extends JPanel {
	private JTextField txtFldIdInstall;
	private JTextField txtFieldRefInstall;
	private JComboBox<Object> comboBoxCodeSoft, comboBoxMatricule, comboBoxCodeOs;
	private DateField dateInstallField = CalendarFactory.createDateField();
	private JTextPane txtPaneComment;
	private JSpinner spinner;
	private AddFrame myParentAddFrame;

	public AddPane(AddFrame p) {
		// réception mypanel pour interagir sur la frame.
		myParentAddFrame = p;

		setBounds(10, 10, 400, 450);
		setLayout(null);

		txtFldIdInstall = new JTextField();
		txtFldIdInstall.setEnabled(false);
		txtFldIdInstall.setBounds(195, 11, 144, 27);
		add(txtFldIdInstall);
		txtFldIdInstall.setColumns(10);

		JLabel lblNewLabel = new JLabel("IdInstallation");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(21, 14, 162, 24);
		add(lblNewLabel);

		// date
		dateInstallField.setBounds(195, 53, 144, 27);
		dateInstallField.setValue(new Date());
		add(dateInstallField);
		// datefield.getValue();

		JLabel lblCommentaires = new JLabel("Commentaires");
		lblCommentaires.setForeground(Color.RED);
		lblCommentaires.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommentaires.setBounds(21, 319, 162, 20);
		add(lblCommentaires);

		JLabel lblDureeinstallation = new JLabel("DureeInstallation");
		lblDureeinstallation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDureeinstallation.setBounds(21, 95, 162, 27);
		add(lblDureeinstallation);

		JLabel lblRefprocedureinstallation = new JLabel("RefProcedureInstallation");
		lblRefprocedureinstallation.setForeground(Color.RED);
		lblRefprocedureinstallation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRefprocedureinstallation.setBounds(21, 135, 162, 27);
		add(lblRefprocedureinstallation);

		txtFieldRefInstall = new JTextField();
		txtFieldRefInstall.setColumns(10);
		txtFieldRefInstall.setBounds(195, 135, 144, 27);
		add(txtFieldRefInstall);

		JLabel lblNewLabel_1 = new JLabel("CodeSoftware");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(21, 183, 162, 22);
		add(lblNewLabel_1);

		JLabel lblMatricule = new JLabel("Matricule");
		lblMatricule.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricule.setBounds(21, 222, 162, 27);
		add(lblMatricule);

		JLabel lblCodeos = new JLabel("CodeOS");
		lblCodeos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodeos.setBounds(10, 273, 173, 22);
		add(lblCodeos);

		JButton btnInserer = new JButton("Insérer");
		btnInserer.setBounds(21, 382, 118, 28);
		btnInserer.addActionListener(new Insert());
		add(btnInserer);

		JButton btnRinitialiser = new JButton("Réinitialiser");
		btnRinitialiser.setBounds(151, 382, 118, 28);
		btnRinitialiser.addActionListener(new Reinit());
		add(btnRinitialiser);

		try {
			txtFldIdInstall.setText(getNextfreeID());
			comboBoxCodeSoft = new JComboBox<Object>(getCodeSoftware());
			comboBoxMatricule = new JComboBox<Object>(getMatricule());
			comboBoxCodeOs = new JComboBox<Object>(getCodeOs());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(myParentAddFrame, "Impossible de charger les ComboBox, erreur: "+e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}

		comboBoxCodeSoft.setBounds(195, 178, 144, 27);
		add(comboBoxCodeSoft);

		comboBoxMatricule.setBounds(195, 222, 144, 27);
		add(comboBoxMatricule);

		comboBoxCodeOs.setBounds(195, 271, 144, 27);
		add(comboBoxCodeOs);

		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new Retour());
		btnRetour.setBounds(281, 382, 90, 28);
		add(btnRetour);

		JLabel lblDateinstallation = new JLabel("DateInstallation");
		lblDateinstallation.setBounds(39, 55, 144, 25);
		add(lblDateinstallation);
		lblDateinstallation.setHorizontalAlignment(SwingConstants.RIGHT);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 9999, 1));
		spinner.setBounds(195, 91, 144, 27);
		add(spinner);

		txtPaneComment = new JTextPane();
		txtPaneComment.setBounds(195, 315, 144, 56);
		add(txtPaneComment);

		JLabel lblChamp = new JLabel("* Champ Facultatif.");
		lblChamp.setForeground(Color.RED);
		lblChamp.setBounds(21, 357, 162, 14);
		add(lblChamp);
	}

	private String getNextfreeID() throws SQLException {
		String toReturn = null;
		String sql = "SELECT MAX(IdInstallation)+1 FROM Installation";
		try {

			Statement prep = myParentAddFrame.getConn().createStatement();
			ResultSet result = prep.executeQuery(sql);
			while (result.next()) {
				toReturn = result.getString(1);
			}
			result.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentAddFrame, "Impossible de charger le prochain ID disponible, erreur:"+e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}

	private Object[] getCodeSoftware() throws SQLException {
		Object[] toReturn = null;
		try {
			PreparedStatement prep = myParentAddFrame.getConn().prepareStatement("SELECT CodeSoftware FROM Software");
			toReturn = AccesBDGen.creerListe1Colonne(prep);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentAddFrame, "Impossible de récupérer les codes de logiciels, erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}

	private Object[] getMatricule() throws SQLException {
		Object[] toReturn = null;
		try {
			PreparedStatement prep = myParentAddFrame.getConn().prepareStatement("SELECT Matricule FROM ResponsableReseaux");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentAddFrame, "Impossible de récupérer les matricules des responsables réseau, erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}

	private Object[] getCodeOs() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = myParentAddFrame.getConn().prepareStatement("SELECT CodeOS FROM OS");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentAddFrame, "Impossible de récupérer les codes des OS, erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}

	private class Retour implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myParentAddFrame.dispose();
		}
	}

	private class Reinit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			reinit();
		}
	}

	public void reinit() {
		try {
			txtFldIdInstall.setText(getNextfreeID());
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(myParentAddFrame, "Impossible de charger le prochain ID disponible, erreur:"+e1, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		
		dateInstallField.setValue(new Date());
		spinner.setValue(0);
		txtPaneComment.setText(null);
		txtFieldRefInstall.setText(null);
		comboBoxCodeSoft.setSelectedIndex(0);
		comboBoxMatricule.setSelectedIndex(0);
		comboBoxCodeOs.setSelectedIndex(0);
	}

	private String setNullIfBlank(String toVerif) {
		if (toVerif.equals("") || toVerif.equals(null))
			return null;
		else
			return toVerif;
	}

	private class Insert implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			PreparedStatement prep;
			int nbModif;

			// format the date for mysql insert
			Date date = (Date) dateInstallField.getValue();
			Timestamp dateInsert = new Timestamp(date.getTime());

			try {
				prep = myParentAddFrame.getConn().prepareStatement(
						"INSERT INTO  Installation VALUES ('"+ getNextfreeID() + "',  '" + dateInsert
								+ "', ? ,  '" + spinner.getValue()
								+ "', ? ,  '"
								+ comboBoxCodeSoft.getSelectedItem().toString()
								+ "',  '"
								+ comboBoxMatricule.getSelectedItem().toString()
								+ "',  '"
								+ comboBoxCodeOs.getSelectedItem().toString()
								+ "')");
				/* to prevent MYSQL INJECTIONS  */
				prep.setString(1, setNullIfBlank(txtPaneComment.getText()));
				prep.setString(2, setNullIfBlank(txtFieldRefInstall.getText()));
				
				nbModif = AccesBDGen.executerInstruction(prep);
				JOptionPane.showMessageDialog(myParentAddFrame, "Ajout réussit avec: "+nbModif+ " ligne(s) modifiée(s).", "Ajout réussit!",JOptionPane.INFORMATION_MESSAGE);
				reinit();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(myParentAddFrame, "Impossible d'ajouter l'enregistrement, erreur: "+e1, "Erreur",JOptionPane.WARNING_MESSAGE);
			}

		}
	}
}
