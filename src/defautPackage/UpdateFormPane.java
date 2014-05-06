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
public class UpdateFormPane extends JPanel{

		private JTextField txtFldIDInstall;
		private JTextField RefProcInstallTxtFld;
		private JComboBox<Object> codeSoftComboBox, matriculeComboBox, codeOsCombobox;
		private DateField dateInstallFld = CalendarFactory.createDateField();
		private JTextPane commentTextPane;
		private JSpinner dureeInstallSpinner;
		private UpdateFrame myFenParent;
		private int idInstall;

		public UpdateFormPane(UpdateFrame p, int index2) {
			// réception mypanel pour interagir sur la frame.
			myFenParent = p;
			idInstall = index2;
			
			
			setBounds(10, 10, 559, 441);
			setLayout(null);

			txtFldIDInstall = new JTextField();
			txtFldIDInstall.setEnabled(false);
			txtFldIDInstall.setBounds(195, 11, 252, 27);
			add(txtFldIDInstall);
			txtFldIDInstall.setColumns(10);

			JLabel lblNewLabel = new JLabel("IdInstallation");
			lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel.setBounds(21, 14, 162, 24);
			add(lblNewLabel);

			// date
			dateInstallFld.setBounds(195, 53, 252, 27);
			dateInstallFld.setValue(new Date());
			add(dateInstallFld);
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

			JLabel lblRefprocedureinstallation = new JLabel(
					"RefProcedureInstallation");
			lblRefprocedureinstallation.setForeground(Color.RED);
			lblRefprocedureinstallation
					.setHorizontalAlignment(SwingConstants.RIGHT);
			lblRefprocedureinstallation.setBounds(21, 135, 162, 27);
			add(lblRefprocedureinstallation);

			RefProcInstallTxtFld = new JTextField();
			RefProcInstallTxtFld.setColumns(10);
			RefProcInstallTxtFld.setBounds(195, 135, 252, 27);
			add(RefProcInstallTxtFld);

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

			JButton btnInserer = new JButton("Modifier");
			btnInserer.setBounds(329, 402, 118, 28);
			btnInserer.addActionListener(new Update());
			add(btnInserer);

			JButton btnRinitialiser = new JButton("Recharger");
			btnRinitialiser.setBounds(195, 402, 118, 28);
			btnRinitialiser.addActionListener(new Rematch());
			add(btnRinitialiser);

			try {
				txtFldIDInstall.setText("");
				codeSoftComboBox = new JComboBox<Object>(getCodeSoftware());
				matriculeComboBox = new JComboBox<Object>(getMatricule());
				codeOsCombobox = new JComboBox<Object>(getCodeOs());
			} catch (SQLException e) {
				e.printStackTrace();
			}

			codeSoftComboBox.setBounds(195, 178, 252, 27);
			add(codeSoftComboBox);

			matriculeComboBox.setBounds(195, 222, 252, 27);
			add(matriculeComboBox);

			codeOsCombobox.setBounds(195, 271, 252, 27);
			add(codeOsCombobox);

			JLabel lblDateinstallation = new JLabel("DateInstallation");
			lblDateinstallation.setBounds(39, 55, 144, 25);
			add(lblDateinstallation);
			lblDateinstallation.setHorizontalAlignment(SwingConstants.RIGHT);

			dureeInstallSpinner = new JSpinner();
			dureeInstallSpinner.setModel(new SpinnerNumberModel(0, 0, 500, 1));
			dureeInstallSpinner.setBounds(195, 91, 252, 27);
			add(dureeInstallSpinner);

			commentTextPane = new JTextPane();
			commentTextPane.setBounds(195, 315, 252, 76);
			add(commentTextPane);

			JLabel lblChamp = new JLabel("* Champ Facultatif.");
			lblChamp.setForeground(Color.RED);
			lblChamp.setBounds(21, 409, 162, 14);
			add(lblChamp);
			/* on remplit les champs */
			try {
				getChamps();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		private void getChamps() throws SQLException {
			
			String sql = "SELECT * FROM Installation WHERE IdInstallation ="+idInstall;
			try {
				Statement prep = myFenParent.getConn().createStatement();
				ResultSet result = prep.executeQuery(sql);
				while (result.next()) {
					
					txtFldIDInstall.setText(result.getString(1));		
					dateInstallFld.setValue(result.getDate(2));
					dureeInstallSpinner.setValue(result.getObject(4));
					RefProcInstallTxtFld.setText(result.getString(5));
					codeSoftComboBox.setSelectedItem(result.getObject(6));
					matriculeComboBox.setSelectedItem(result.getObject(7));
					codeOsCombobox.setSelectedItem(result.getObject(8));
					commentTextPane.setText(result.getString(3));
				}
				result.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
			}
			

		}

		private Object[] getCodeSoftware() throws SQLException {
			Object[] toReturn = null;
			try {
				PreparedStatement prep = myFenParent.getConn().prepareStatement("SELECT CodeSoftware FROM Software");
				toReturn = AccesBDGen.creerListe1Colonne(prep);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
			}
			return toReturn;

		}

		private Object[] getMatricule() throws SQLException {
			Object[] toReturn = null;
			try {
				PreparedStatement prep = myFenParent.getConn().prepareStatement(
						"SELECT Matricule FROM ResponsableReseaux");
				toReturn = AccesBDGen.creerListe1Colonne(prep);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
			}
			return toReturn;

		}

		private Object[] getCodeOs() throws SQLException {
			Object[] toReturn = null;
			PreparedStatement prep;
			try {
				prep = myFenParent.getConn().prepareStatement("SELECT CodeOS FROM OS");
				toReturn = AccesBDGen.creerListe1Colonne(prep);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
			}
			return toReturn;

		}

		private String setNullIfBlank(String toVerif) {
			if (toVerif.equals("") || toVerif.equals(null))
				return null;
			else
				return toVerif;
		}
		
		private class Rematch implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					getChamps();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1, "Erreur",JOptionPane.WARNING_MESSAGE);
				}

			}
		}

		private class Update implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement prep;
				int nbModif;

				// format the date for mysql update
				Date date = (Date) dateInstallFld.getValue();
				Timestamp dateInsert = new Timestamp(date.getTime());
				
				try {
					prep = myFenParent.getConn().prepareStatement("UPDATE Installation SET DateInstallation = '"+dateInsert+"', Commentaires = ?, DureeInstallation = '"+dureeInstallSpinner.getValue()+"', RefProcedureInstallation = ?, CodeSoftware = '"+codeSoftComboBox.getSelectedItem().toString()+"', Matricule = '"+matriculeComboBox.getSelectedItem().toString()+"', CodeOS = '"+codeOsCombobox.getSelectedItem().toString()+"' WHERE IdInstallation="+idInstall);
					prep.setString(1, setNullIfBlank(commentTextPane.getText()));
					prep.setString(2, setNullIfBlank(RefProcInstallTxtFld.getText()));
					nbModif = AccesBDGen.executerInstruction(prep);
					
					JOptionPane.showMessageDialog(null, nbModif+" ligne(s) modifiée(s).", "Modification réussie!",JOptionPane.INFORMATION_MESSAGE);
					getChamps();
					myFenParent.updateTabpane();
					} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1, "Erreur",JOptionPane.WARNING_MESSAGE);
				}

			}
		}
	}
