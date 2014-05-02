package defautPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import AccesBD.AccesBDGen;
import javax.swing.JButton;



@SuppressWarnings("serial")
public class UpdateTablePane extends JPanel {
			private static JTable table;
			@SuppressWarnings("rawtypes")
			private JComboBox comboBox;
			private JScrollPane scrollPane;
			private JLabel lblEditeur;
			private UpdateFrame myParentFen;

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public UpdateTablePane(UpdateFrame  f) {
				myParentFen = f;
				this.setBounds(10, 10, 686, 424);
				this.setLayout(null);

				try {
					comboBox = new JComboBox(getEditeur());

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e, "Erreur",
							JOptionPane.WARNING_MESSAGE);
				}

				comboBox.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						updateTable();
					}
				});

				comboBox.setBounds(66, 12, 197, 20);
				this.add(comboBox);

				// table
				table = new JTable(null);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setBackground(Color.white);
			

				updateTable();
				// JscrollPane pane
				scrollPane = new JScrollPane(table);
				scrollPane.setBounds(10, 43, 666, 370);
				this.add(scrollPane);

				lblEditeur = new JLabel("Editeur:");
				lblEditeur.setBounds(10, 15, 46, 14);
				this.add(lblEditeur);
				
				JButton btnModifier = new JButton("Modifier la selection");
				btnModifier.setBounds(527, 11, 134, 23);
				btnModifier.addActionListener(new Update());
				add(btnModifier);

			}

			
			private class Update implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					newTab();
					updateTable();
				}
			}

			public void updateTable() {
				try {
					PreparedStatement prep = myParentFen
							.getConn()
							.prepareStatement(
									"SELECT IdInstallation, DateInstallation, Installation.CodeSoftware, Installation.CodeOS FROM Installation JOIN Software ON Installation.CodeSoftware = Software.CodeSoftware JOIN Editeur ON Editeur.CodeEdit = Software.CodeEdit WHERE Designation ='"+ comboBox.getSelectedItem().toString()	+ "'");
					table.setModel(AccesBDGen.creerTableModel(prep));
					centerJtable(table);

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1, "Erreur",
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
			
			

			
			private Object[] getEditeur() throws SQLException {
				Object[] toReturn = null;
				PreparedStatement prep;
				try {
					prep = myParentFen.getConn().prepareStatement(
							"SELECT Designation FROM Editeur");
					toReturn = AccesBDGen.creerListe1Colonne(prep);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e, "Erreur",
							JOptionPane.WARNING_MESSAGE);
				}
				return toReturn;

			}

			private void centerJtable(JTable table) {
				DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
				custom.setHorizontalAlignment(JLabel.CENTER);
				for (int i = 0; i < table.getColumnCount(); table.getColumnModel().getColumn(i).setCellRenderer(custom), i++);
			}

			public int newTab() {
				int idInstall = -1;
				try {
					int rowcheck = table.getSelectedRow();
					idInstall = (int) table.getValueAt(table.getSelectedRow(), 0);
					if (rowcheck > -1) {
						myParentFen.newtab(idInstall);
						JOptionPane.showMessageDialog(null,"l'enregistrement a bien été chargé sous l'onglet Modification","Selection réussie", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					JOptionPane.showMessageDialog(null,
							"Aucune sélection, veuillez sélectionner une ligne!",
							"Erreur", JOptionPane.WARNING_MESSAGE);
				}
				updateTable();
				return idInstall;
			}
}