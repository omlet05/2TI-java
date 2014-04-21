package defaultt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.sql.*;
import java.text.SimpleDateFormat;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;



import java.util.Date;
import java.awt.Color;

public class AddWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_5;
	private JComboBox<?> comboBox, comboBox_1, comboBox_2;
	private DateField datefield = CalendarFactory.createDateField();
	private JTextPane textPane;
	private JSpinner spinner;

	public AddWindow() {
		setTitle("Ajouter une installation.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 450);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(195, 11, 144, 27);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("IdInstallation");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(21, 14, 162, 24);
		contentPane.add(lblNewLabel);
		
		//date
		datefield.setBounds(195, 53, 144, 27);
		datefield.setValue(new Date());
		contentPane.add(datefield);
		//datefield.getValue();
		
		
		JLabel lblCommentaires = new JLabel("Commentaires");
		lblCommentaires.setForeground(Color.RED);
		lblCommentaires.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCommentaires.setBounds(21, 319, 162, 20);
		contentPane.add(lblCommentaires);
		
		JLabel lblDureeinstallation = new JLabel("DureeInstallation");
		lblDureeinstallation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDureeinstallation.setBounds(21, 95, 162, 27);
		contentPane.add(lblDureeinstallation);
		
		JLabel lblRefprocedureinstallation = new JLabel("RefProcedureInstallation");
		lblRefprocedureinstallation.setForeground(Color.RED);
		lblRefprocedureinstallation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRefprocedureinstallation.setBounds(21, 135, 162, 27);
		contentPane.add(lblRefprocedureinstallation);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(195, 135, 144, 27);
		contentPane.add(textField_5);
		
		JLabel lblNewLabel_1 = new JLabel("CodeSoftware");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(21, 183, 162, 22);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMatricule = new JLabel("Matricule");
		lblMatricule.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMatricule.setBounds(21, 222, 162, 27);
		contentPane.add(lblMatricule);
		
		JLabel lblCodeos = new JLabel("CodeOS");
		lblCodeos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCodeos.setBounds(10, 273, 173, 22);
		contentPane.add(lblCodeos);
		
		JButton btnInserer = new JButton("Insérer");
		btnInserer.setBounds(21, 382, 118, 28);
		btnInserer.addActionListener(new Insert());
		contentPane.add(btnInserer);
		
		JButton btnRinitialiser = new JButton("Réinitialiser");
		btnRinitialiser.setBounds(151, 382, 118, 28);
		btnRinitialiser.addActionListener(new Reinit());
		contentPane.add(btnRinitialiser);
				
		try {
			textField.setText(getNextfreeID());
			comboBox = new JComboBox(getCodeSoftware());
			comboBox_1 = new JComboBox(getMatricule());
			comboBox_2 = new JComboBox(getCodeOs());
		} catch (SQLException e) {
			e.printStackTrace();
			dispose();
		}
		
		comboBox.setBounds(195, 178, 144, 27);
		contentPane.add(comboBox);
				
		comboBox_1.setBounds(195, 222, 144, 27);
		contentPane.add(comboBox_1);
				
		comboBox_2.setBounds(195, 271, 144, 27);
		contentPane.add(comboBox_2);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnRetour.setBounds(281, 382, 90, 28);
		contentPane.add(btnRetour);
		
		JLabel lblDateinstallation = new JLabel("DateInstallation");
		lblDateinstallation.setBounds(39, 55, 144, 25);
		contentPane.add(lblDateinstallation);
		lblDateinstallation.setHorizontalAlignment(SwingConstants.RIGHT);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 500, 1));
		spinner.setBounds(195, 91, 144, 27);
		contentPane.add(spinner);
		
		textPane = new JTextPane();
		textPane.setBounds(195, 315, 144, 56);
		contentPane.add(textPane);
		
		JLabel lblChamp = new JLabel("* Champ Facultatif.");
		lblChamp.setForeground(Color.RED);
		lblChamp.setBounds(21, 357, 162, 14);
		contentPane.add(lblChamp);
		
		
		
		
		setVisible(true);
	}
	
	private String getNextfreeID() throws SQLException{
		String toReturn = null;
		String sql = "SELECT MAX(IdInstallation)+1 FROM Installation";
		try {
			
			 Statement prep = MainWindow.conn.createStatement();
			 ResultSet result = prep.executeQuery(sql);
			 while(result.next()){
				 toReturn = result.getString(1);
		        }
			result.close();
		
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;
		
	}
	
	private Object[] getCodeSoftware() throws SQLException{
		Object[] toReturn = null;
		try {
			PreparedStatement prep = MainWindow.conn.prepareStatement("SELECT CodeSoftware FROM Software");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;
		
	}
	private Object[] getMatricule() throws SQLException{
		Object[] toReturn = null;
		try {
			PreparedStatement prep = MainWindow.conn.prepareStatement("SELECT Matricule FROM ResponsableReseaux");
			toReturn =AccesBDGen.creerListe1Colonne(prep);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
	return toReturn;
		
	}
	private Object[] getCodeOs() throws SQLException{
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = MainWindow.conn.prepareStatement("SELECT CodeOS FROM OS");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur", JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;
		
	}
	
	private class Reinit implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			try {
				textField.setText(getNextfreeID());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
 			//textField_1.setText("");
 			datefield.setValue(new Date());
 			spinner.setValue(0);
 			textPane.setText(null);
 			textField_5.setText(null);
 			comboBox.setSelectedIndex(0);
 			comboBox_1.setSelectedIndex(0);
 			comboBox_2.setSelectedIndex(0);
 			
 		}
 	}
	
	private class Insert implements ActionListener {
 		public void actionPerformed(ActionEvent e){
 			PreparedStatement prep;
 			int modif;
 			String commentInsert = null, refProcInsert = null;
 			
 			//set to null blank field
 			//Commentaires 
 			if(!(textPane.getText().equals("") || textPane.getText().equals(null)))
 				commentInsert = textPane.getText();
 			if(!(textField_5.getText().equals("") || textField_5.getText().equals(null)))
 				refProcInsert = textField_5.getText();
 			
 			
 			
 			//format the date
 			Date date = (Date) datefield.getValue();
 			Timestamp dateInsert = new Timestamp(date.getTime());
 			System.out.println(dateInsert);
 			
 			try {
 				prep = MainWindow.conn.prepareStatement("INSERT INTO  `test`.`Installation` VALUES ('"+getNextfreeID()+"',  '"+dateInsert+"', ? ,  '"+spinner.getValue()+"', ? ,  '"+comboBox.getSelectedItem().toString()+"',  '"+comboBox_1.getSelectedItem().toString()+"',  '"+comboBox_2.getSelectedItem().toString()+"')");
 				prep.setString(1, commentInsert);
 				prep.setString(2, refProcInsert);
 				modif = AccesBDGen.executerInstruction(prep);
 				JOptionPane.showMessageDialog(null, modif+" ligne(s) modifiée(s).", "Ajout réussit!", JOptionPane.INFORMATION_MESSAGE);
 				//must reset form
 			}
 			catch (Exception e1) {
 				JOptionPane.showMessageDialog(null, e1, "Erreur", JOptionPane.WARNING_MESSAGE);
 			}
 			
 			
 		}
 	}
}
