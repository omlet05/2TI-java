package defaultt;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

import AccesBD.*;


public class DelPane extends JPanel {
	private static JTable table;
	private JComboBox comboBox;
	private JScrollPane scrollPane;
	private JLabel lblEditeur;

	
	public DelPane() {
		 	this.setBounds(10,10,753,462); 
		 	this.setBorder(BorderFactory.createLineBorder(Color.BLUE)); 
		 	this.setLayout(null);

			
			try {
				comboBox = new JComboBox(getEditeur());
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
			}
			
			comboBox.addActionListener (new ActionListener () {
			    public void actionPerformed(ActionEvent e) {
			        updateTable();
			    }
			});
			
			comboBox.setBounds(514, 10, 231, 20);
			this.add(comboBox);
			
			
			
			
			//table
			table = new JTable(null);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBackground(Color.white);
			updateTable();
			//JscrollPane pane
			scrollPane = new JScrollPane(table);			
			scrollPane.setBounds(10, 43, 735, 375);
			this.add(scrollPane);
			
			JButton btnSupprimer = new JButton("Supprimer la selection");
			btnSupprimer.addActionListener(new Del());
			btnSupprimer.setBounds(608, 429, 137, 23);
			this.add(btnSupprimer);
			
			lblEditeur = new JLabel("Editeur:");
			lblEditeur.setBounds(458, 13, 46, 14);
			this.add(lblEditeur);
			
				
			}
	private void updateTable(){
		try {
			//PreparedStatement prep = MainFrame.conn.prepareStatement("SELECT IdInstallation, DateInstallation, Software.CodeSoftware, CodeOS FROM Installation JOIN Software ON Installation.CodeSoftware = Software.CodeSoftware JOIN Editeur ON Editeur.CodeEdit = Software.CodeEdit WHERE Designation ='"+comboBox.getSelectedItem().toString()+"'");
			PreparedStatement prep = MainFrame.conn.prepareStatement("SELECT * FROM Installation JOIN Software ON Installation.CodeSoftware=Software.CodeSoftware JOIN Editeur ON Editeur.CodeEdit=Software.CodeEdit  WHERE Designation='"+comboBox.getSelectedItem().toString()+"'");
			table.setModel(AccesBDGen.creerTableModel(prep));
			
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		}
		
		private class Del implements ActionListener{
	 		public void actionPerformed(ActionEvent e){
	 			deleteRow();
	 			updateTable();
	 		}
	 	}
		
		
		private void deleteRow(){ //must add exception if null
            try {
            	
            	int rowcheck = table.getSelectedRow();
            	int idInstall = (int) table.getValueAt(table.getSelectedRow(), 0);
            	
                
	                if(rowcheck > -1){
	                	PreparedStatement prep = MainFrame.conn.prepareStatement("DELETE FROM Installation WHERE Installation.IdInstallation = "+idInstall);
	                	AccesBDGen.executerInstruction(prep);
	                	JOptionPane.showMessageDialog(null, "l'enregistrement a bien été supprimé", "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
	                }
            }
            catch (ArrayIndexOutOfBoundsException  e){
            	JOptionPane.showMessageDialog(null, "Aucune sélection, veuillez sélectionner une ligne!", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
            catch (SQLException e){
            	JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
            }
            updateTable(); 
	}
		
		private Object[] getEditeur() throws SQLException{
			Object[] toReturn = null;
			PreparedStatement prep;
			try {
				prep = MainFrame.conn.prepareStatement("SELECT Designation FROM Editeur");
				toReturn = AccesBDGen.creerListe1Colonne(prep);
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
			}
			return toReturn;
			
		}

	}

