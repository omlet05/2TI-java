package defautPackage;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import AccesBD.AccesBDGen;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Search2Pane extends JPanel {
	private static JTable table;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox2;
	private JScrollPane scrollPane;
	private JLabel lblResponsable;
	private Search2Frame myParentS2;
	private JButton btnRetourS2, btnFiltrer;
	private JLabel lblDate;
	private DateField datefield = CalendarFactory.createDateField();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Search2Pane(Search2Frame p) {
		myParentS2 = p;
		this.setBounds(10, 10, 1000,550);
		this.setLayout(null);

		try {
			comboBox2 = new JComboBox(getResponsable());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}
		
		
		datefield.setBounds(648, 40, 194, 20);
		datefield.setValue(new Date());
		add(datefield);
		

		comboBox2.setBounds(648, 12, 194, 20);
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

		
		lblResponsable = new JLabel("Responsable:");
		lblResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResponsable.setBounds(508, 15, 122, 14);
		this.add(lblResponsable);
		
		lblDate = new JLabel("Date d'installation:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(488, 46, 142, 14);
		this.add(lblDate);
		
		btnFiltrer = new JButton("Filtrer");
		btnFiltrer.addActionListener(new Update());
		btnFiltrer.setBounds(854, 25, 117, 25);
		add(btnFiltrer);

	}

	private void updateTable() {
		try {
			Date date = (Date) datefield.getValue();
			java.sql.Date dateSQL = new java.sql.Date(date.getTime());
			PreparedStatement prep = myParentS2.getConn().prepareStatement("SELECT * FROM ResponsableReseaux JOIN Installation i on ResponsableReseaux.Matricule = i.Matricule WHERE ResponsableReseaux.NomPrenom = '"+ comboBox2.getSelectedItem().toString()+"' AND DateInstallation >= ?");	
			prep.setDate(1, dateSQL);
			table.setModel(AccesBDGen.creerTableModel(prep));
			centerJtable(table);

		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
	}
	 
	


	private class Retour implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			myParentS2.dispose();
		}
	}

	private class Update implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			updateTable();
		}
	}

	

	private Object[] getDate() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prep;
		
		try {
			prep = myParentS2.getConn().prepareStatement("SELECT DateInstallation from Installation ");
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
			prep = myParentS2.getConn().prepareStatement("SELECT NomPrenom from ResponsableReseaux ");
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

