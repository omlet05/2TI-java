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
		btnFiltrer.addActionListener(new GetJTable());
		contentPane.add(btnFiltrer);
		
		
		//table
		try {
			PreparedStatement reqStat = MainWindow.conn.prepareStatement("SELECT * FROM Editeur");
			MonTableModel result = AccesBDGen.creerTableModel(reqStat);
			table = new JTable(result);
			//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			//table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBackground(Color.white);
			//JscrollPane pane
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 428, 719, -375);
			contentPane.add(scrollPane);
		}
		catch (SQLException e2){
			JOptionPane.showMessageDialog(null, e2, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		
		
		
		
		
		
		
		
		
		
		setVisible(true);
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
	private class GetJTable implements ActionListener{
		MonTableModel toReturn = null;
		PreparedStatement prep;
		public MonTableModel getJTable(){
			
			try {
				prep = MainWindow.conn.prepareStatement("SELECT * FROM Software soft INNER JOIN Editeur edit ON edit.Designation = '"+comboBox.getSelectedItem().toString()+"'");
				toReturn = AccesBDGen.creerTableModel(prep);
				
			}
			catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
			}
			
			
			return toReturn;
		}
		public void actionPerformed(ActionEvent e) {
			scrollPane = new JScrollPane(table);	
		}
	}
}
