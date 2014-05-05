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
public class Search1Pane extends JPanel {
	
	private JButton btnBackS1;
	private Search1Frame search1Frame;
	private static JTable tableS;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel lblAnneeEtude;
	private JScrollPane scrollPane;
	@SuppressWarnings("unchecked")
	
	public Search1Pane(Search1Frame search1Fram){
		
		this.setBounds(10, 10, 1000, 550);
		setLayout(null);
		search1Frame =search1Fram;
		
		try {
			comboBox = new JComboBox(getAnneeEtude());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		comboBox.setBounds(800, 10, 150, 20);
		this.add(comboBox);

		
		
		tableS = new JTable(null);
		tableS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableS.setBackground(Color.white);
		updateTable();
		
		btnBackS1 = new JButton("Retour");
		btnBackS1.addActionListener(new Back());
		btnBackS1.setBounds(900, 475, 65, 23);
		add(btnBackS1);
		
		scrollPane = new JScrollPane(tableS);
		scrollPane.setBounds(10, 43, 940, 400);
		this.add(scrollPane);
		
		lblAnneeEtude = new JLabel("Année d'étude :");
		lblAnneeEtude.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnneeEtude.setBounds(645, 13, 150, 14);
		this.add(lblAnneeEtude);
		
		
	}
	
	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			search1Frame.dispose();
		}
	}
	
	private void updateTable() {
		try {
			PreparedStatement prepS = search1Frame.getConn().prepareStatement("SELECT Nom, CodeInstallation, Software.CodeSoftware, a.Annee, a.CodeSection  FROM AnneeEtude a JOIN UtilisationSoftware ON a.IdAnneeEtude = UtilisationSoftware.IdAnneeEtude JOIN Software  ON UtilisationSoftware.CodeSoftware = Software.CodeSoftware  where Software.CodeInstallation is NULL and a.Annee= '"+ comboBox.getSelectedItem().toString()+"'");			
			tableS.setModel(AccesBDGen.creerTableModel(prepS));
			centerJtable(tableS);
			

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
	}

	private Object[] getAnneeEtude() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prepS;
		try {
			String sqlInstruction="SELECT DISTINCT Annee FROM AnneeEtude ";
			prepS = search1Frame.getConn().prepareStatement(sqlInstruction);
			
			toReturn = AccesBDGen.creerListe1Colonne(prepS);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}
	
	
	private void centerJtable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.HORIZONTAL);
		for (int i = 0; i < table.getColumnCount(); table.getColumnModel().getColumn(i).setCellRenderer(custom), i++);
	}
}
