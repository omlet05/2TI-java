package defaultt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DelWindow extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JComboBox comboBox;

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
		lblNewLabel.setBounds(351, 15, 47, 14);
		contentPane.add(lblNewLabel);
		
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
		btnFiltrer.addActionListener(new GetJTable());
		contentPane.add(btnFiltrer);
		
		table = new JTable();
		table.setBounds(10, 421, 718, -362);
		contentPane.add(table);
		
		
		
		setVisible(true);
	}
	
	private Object[] getEditeur() throws SQLException{
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = MainWindow.conn.prepareStatement("SELECT Designation FROM editeur");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;
		
	}
	private class GetJTable implements ActionListener{
		MonTableModel toReturn = null;
		PreparedStatement prep;
		public void actionPerformed(ActionEvent e){
			
			try {
				prep = MainWindow.conn.prepareStatement("SELECT * FROM software soft INNER JOIN editeur edit ON edit.Designation = '"+comboBox.getSelectedItem().toString()+"'");
				toReturn = AccesBDGen.creerTableModel(prep);
				
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
			}
			
			table = new JTable(toReturn);	
			System.out.println(table);
		}
	}
}
