package defautPackage;
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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import net.sf.nachocalendar.CalendarFactory;
import net.sf.nachocalendar.components.DateField;
import AccesBD.AccesBDGen;

@SuppressWarnings("serial")
public class Search2Pane extends JPanel {
	private static JTable table;
	private JComboBox<Object> comboBox2;
	private JScrollPane scrollPane;
	private JLabel lblResponsable;
	private SearchFrame myParentS2;
	private JButton btnRetourS2, btnFiltrer;
	private JLabel lblDate;
	private DateField datefield = CalendarFactory.createDateField();

	public Search2Pane(SearchFrame p) {
		myParentS2 = p;
		this.setBounds(0, 0, 1000,520);
		this.setLayout(null);

		try {
			comboBox2 = new JComboBox<Object>(getResponsable());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e, "Erreur",
					JOptionPane.WARNING_MESSAGE);
		}
		datefield.getFormattedTextField().setHorizontalAlignment(SwingConstants.CENTER);
		
		
		datefield.setBounds(650, 13, 194, 20);
		datefield.setValue(new Date());
		add(datefield);
		

		comboBox2.setBounds(650, 44, 194, 20);
		this.add(comboBox2);
		// table
		table = new JTable(null);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.white);

		updateTable();
		
		JLabel lblInfos = new JLabel("<html><i>\"Danc cette fenêtre vous pouvez lister les installations effectuées après une date introduite par vos soins et  qui ont été conduites par un responsable réseaux définit.\"</i></html>");
		lblInfos.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblInfos.setBounds(10, 13, 501, 41);
		add(lblInfos);
		// JscrollPane pane
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 70, 940, 400);
		this.add(scrollPane);

		btnRetourS2 = new JButton("Retour");
		btnRetourS2.setBounds(873, 486, 117, 23);
		btnRetourS2.addActionListener(new Retour());
		this.add(btnRetourS2);

		
		lblResponsable = new JLabel("Responsable:");
		lblResponsable.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResponsable.setBounds(510, 47, 122, 14);
		this.add(lblResponsable);
		
		lblDate = new JLabel("Date d'installation:");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setBounds(490, 19, 142, 14);
		this.add(lblDate);
		
		btnFiltrer = new JButton("Filtrer");
		btnFiltrer.addActionListener(new Update());
		btnFiltrer.setBounds(873, 19, 117, 25);
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

