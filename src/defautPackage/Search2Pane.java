package defautPackage;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import AccesBD.AccesBDGen;

@SuppressWarnings("serial")
public class Search2Pane extends JPanel {
	private static JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JComboBox comboBox2;
	private JScrollPane scrollPane;
	private JLabel lblEditeur;
	private Search2Frame myParentS2;
	private JButton btnRetourS2;
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Search2Pane(Search2Frame p) {
		myParentS2 = p;
		this.setBounds(10, 10, 1000,550);
		this.setLayout(null);

		try {
			comboBox = new JComboBox(getDate());
			comboBox2 = new JComboBox(getResponsable());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}

		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		comboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		
		
		
		
		
		comboBox.setBounds(514, 40, 231, 20);
		this.add(comboBox);

		comboBox2.setBounds(714, 10, 231, 20);
		this.add(comboBox2);
		// table
		table = new JTable(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.white);

		updateTable();
		// JscrollPane pane
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 70, 940, 400);
		this.add(scrollPane);

		btnRetourS2 = new JButton("Retour");
		btnRetourS2.setBounds(900, 475, 65, 23);
		btnRetourS2.addActionListener(new Retour());
		this.add(btnRetourS2);

		
		lblEditeur = new JLabel("");
		lblEditeur.setBounds(458, 13, 46, 14);
		this.add(lblEditeur);

	}

	private void updateTable() {
		try {
			PreparedStatement prep = myParentS2.getConn().prepareStatement("SELECT * FROM installation i join responsablereseaux on responsablereseaux.matricule=i.matricule WHERE responsablereseaux.NomPrenom='"+ comboBox2.getSelectedItem().toString()+"'"+"and i.dateinstallation ="+comboBox.getSelectedItem().toString() );	
			table.setModel(AccesBDGen.creerTableModel(prep));
			centerJtable(table);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1, "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	


	private class Retour implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myParentS2.dispose();
		}
	}

	

	private Object[] getDate() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = myParentS2.getConn().prepareStatement("SELECT dateinstallation from installation ");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}
	private Object[] getResponsable() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prep;
		try {
			prep = myParentS2.getConn().prepareStatement("SELECT nomprenom from responsablereseaux ");
			toReturn = AccesBDGen.creerListe1Colonne(prep);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}

	private void centerJtable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < table.getColumnCount(); table.getColumnModel()
				.getColumn(i).setCellRenderer(custom), i++)
			;
	}

}

