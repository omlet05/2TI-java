package defautPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import AccesBD.AccesBDGen;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DelPane extends JPanel {
	private static JTable table;
	private JComboBox<Object> comboBox;
	private JScrollPane scrollPane;
	private JLabel lblEditeur;
	private DelFrame myParentFen;
	private JButton btnRetour;

	public DelPane(DelFrame p) {
		myParentFen = p;
		this.setBounds(10, 10, 753, 462);
		this.setLayout(null);

		try {
			comboBox = new JComboBox<Object>(getEditeur());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});

		comboBox.setBounds(514, 10, 231, 20);
		this.add(comboBox);

		// table
		table = new JTable(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.white);

		updateTable();
		// JscrollPane pane
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 43, 735, 375);
		this.add(scrollPane);

		btnRetour = new JButton("Retour");
		btnRetour.setBounds(10, 429, 137, 23);
		btnRetour.addActionListener(new Retour());
		this.add(btnRetour);

		JButton btnSupprimer = new JButton("Supprimer la selection");
		btnSupprimer.setBounds(528, 429, 217, 23);
		btnSupprimer.addActionListener(new Del());
		this.add(btnSupprimer);

		lblEditeur = new JLabel("Editeur:");
		lblEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditeur.setBounds(396, 13, 108, 14);
		this.add(lblEditeur);

	}

	private void updateTable() {
		try {
			PreparedStatement prep = myParentFen.getConn().prepareStatement("SELECT IdInstallation, DateInstallation, Installation.CodeSoftware, Installation.CodeOS FROM Installation JOIN Software ON Installation.CodeSoftware = Software.CodeSoftware JOIN Editeur ON Editeur.CodeEdit = Software.CodeEdit WHERE Designation ='"+ comboBox.getSelectedItem().toString()+ "'");
			table.setModel(AccesBDGen.creerTableModel(prep));
			centerJtable(table);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(myParentFen, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
	}

	private class Del implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteRow();
			updateTable();
		}
	}

	private class Retour implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myParentFen.dispose();
		}
	}

	private void deleteRow() {
		
			try {
	
				int rowcheck = table.getSelectedRow();
				int idInstall = (int) table.getValueAt(table.getSelectedRow(), 0);
	
				if (rowcheck > -1) {
					int confirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer cette entrée?", "Confirmation de suppression", JOptionPane.YES_NO_OPTION);
					if(confirmation == 0){
						PreparedStatement prep = myParentFen.getConn().prepareStatement("DELETE FROM Installation WHERE Installation.IdInstallation = "+ idInstall);
						AccesBDGen.executerInstruction(prep);
						JOptionPane.showMessageDialog(myParentFen,"l'enregistrement a bien été supprimé","Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(myParentFen,"Aucune sélection, veuillez sélectionner une ligne!","Erreur", JOptionPane.WARNING_MESSAGE);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(myParentFen, e, "Erreur",JOptionPane.WARNING_MESSAGE);
			}
			finally{
				updateTable();
			}
	}

	private Object[] getEditeur() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = myParentFen.getConn().prepareStatement("SELECT Designation FROM Editeur");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentFen, "Impossible de récupérer les éditeurs erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}

	private void centerJtable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); table.getColumnModel().getColumn(i).setCellRenderer(custom), i++);
	}

}
