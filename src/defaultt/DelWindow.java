package defaultt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class DelWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JComboBox comboBox;
	private JScrollPane scrollPane;

	public DelWindow() {
		setTitle("Supression d'un software.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(771, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editeur:");
		
		
		try {
			comboBox = new JComboBox(getEditeur());
			
		} catch (SQLException e) {
			e.printStackTrace();
			dispose();
		}
		comboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		        updateTable();
		    }
		});
		
		comboBox.setBounds(514, 10, 231, 20);
		contentPane.add(comboBox);
		
		
		
		
		//table
		table = new JTable(null);
		
		//table.setFillsViewportHeight(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.white);
		updateTable();
		//JscrollPane pane
		scrollPane = new JScrollPane(table);			
		scrollPane.setBounds(10, 43, 735, 375);
		contentPane.add(scrollPane);
		
		JButton btnSupprimer = new JButton("Supprimer la selection");
		btnSupprimer.addActionListener(new Del());
		btnSupprimer.setBounds(608, 429, 137, 23);
		contentPane.add(btnSupprimer);
		
		JLabel lblEditeur = new JLabel("Editeur:");
		lblEditeur.setBounds(458, 13, 46, 14);
		contentPane.add(lblEditeur);
		
		//add setText to the JtextField by selection
		/*table.getSelectionModel().addListSelectionListener((new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent event) {
				int row = (int) table.getValueAt(table.getSelectedRow(), 0);
				//textField.setText(String.valueOf(row));
			}
		}));*/

		setVisible(true);
	}
	
	private class Del implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			deleteRow();
 			updateTable();
 		}
 	}
	
	private Object[] getEditeur() throws SQLException{
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = MainWindow.conn.prepareStatement("SELECT Designation FROM Editeur");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;
		
	}
	
	private void deleteRow(){ //must add exception if null
            try {
            	int idInstall = -1;
            	idInstall = (int) table.getValueAt(table.getSelectedRow(), 0);
            	
                
	                if(idInstall == -1){
	                	JOptionPane.showMessageDialog(null, "Aucune selection!", "Erreur", JOptionPane.WARNING_MESSAGE);
	                }
                else{
                	System.out.println(idInstall);
                	//PreparedStatement prep = MainWindow.conn.prepareStatement("DELETE FROM Installation WHERE Installation.IdInstallation = "+idInstall);
                	//AccesBDGen.executerInstruction(prep);
                }
                JOptionPane.showMessageDialog(null, "l'enregistrement a bien été supprimé", "Suppression réussie", JOptionPane.INFORMATION_MESSAGE);
                
            }
            catch (Exception e){
            	JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
            }
            updateTable(); 
	}
	
	
    
	
		
	private void updateTable(){
		try {
			PreparedStatement prep = MainWindow.conn.prepareStatement("SELECT * FROM Installation JOIN Software ON Installation.CodeSoftware=Software.CodeSoftware JOIN Editeur ON Editeur.CodeEdit=Software.CodeEdit  WHERE Designation='"+comboBox.getSelectedItem().toString()+"'");
			table.setModel(AccesBDGen.creerTableModel(prep));
			
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		
	}
}
