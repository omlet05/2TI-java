package defaultt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class DelWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JComboBox comboBox;
	private JScrollPane scrollPane;

	public DelWindow() {
		setTitle("Supression d'un software.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(771, 481);
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
		
		
		comboBox.setBounds(408, 12, 233, 20);
		contentPane.add(comboBox);
		
		JButton btnFiltrer = new JButton("Filtrer");
		btnFiltrer.setBounds(665, 11, 80, 23);
		btnFiltrer.addActionListener(new Filter());
		contentPane.add(btnFiltrer);
		
		
		//table
			table = new JTable(null);
			
			//table.setFillsViewportHeight(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBackground(Color.white);
			//JscrollPane pane
			scrollPane = new JScrollPane(table);			
			scrollPane.setBounds(10, 51, 735, 380);
			contentPane.add(scrollPane);

		setVisible(true);
	}
	
	
	private class Filter implements ActionListener{
 		public void actionPerformed(ActionEvent e){
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
	
		
	private void updateTable(){
		try {
			PreparedStatement prep = MainWindow.conn.prepareStatement("SELECT * FROM Software INNER JOIN Editeur ON Software.CodeEdit=Editeur.CodeEdit INNER JOIN Installation ON Software.CodeSoftware=Installation.CodeSoftware WHERE Designation='"+comboBox.getSelectedItem().toString()+"'");
			table.setModel(AccesBDGen.creerTableModel(prep));
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		
	}
}
