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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import AccesBD.AccesBDGen;
import javax.swing.border.LineBorder;



@SuppressWarnings("serial")
public class Search1Pane extends JPanel {
	
	private JButton btnBackS1;
	private SearchFrame myParentsearchFrame;
	private static JTable tableS;
	private JComboBox<Object> comboBoxAnneeEtude;
	private JLabel lblAnneeEtude;
	private JScrollPane scrollPane;
	private JComboBox<Object> comboBoxCodeSection;
	private JLabel lblSection;
	
	public Search1Pane(SearchFrame search1Fram){
		
		this.setBounds(0, 0, 1000, 520);
		setLayout(null);
		myParentsearchFrame = search1Fram;
		
		try {
			comboBoxAnneeEtude = new JComboBox<Object>(getAnneeEtude());
			comboBoxCodeSection =new JComboBox<Object>(getCodeSection());

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(myParentsearchFrame, "Impossible de charger les ComboBox erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		comboBoxAnneeEtude.setBounds(800, 10, 150, 20);
		this.add(comboBoxAnneeEtude);
		comboBoxAnneeEtude.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		

		
		
		tableS = new JTable(null);
		tableS.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tableS.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableS.setBackground(Color.white);
		updateTable();
		
		btnBackS1 = new JButton("Retour");
		btnBackS1.addActionListener(new Back());
		btnBackS1.setBounds(875, 486, 115, 23);
		add(btnBackS1);
		
		scrollPane = new JScrollPane(tableS);
		scrollPane.setBounds(10, 65, 940, 400);
		this.add(scrollPane);
		
		lblAnneeEtude = new JLabel("Année d'étude :");
		lblAnneeEtude.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAnneeEtude.setBounds(645, 13, 150, 14);
		this.add(lblAnneeEtude);
		
		lblSection = new JLabel("Section :");
		lblSection.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSection.setBounds(645, 33, 150, 14);
		this.add(lblSection);
		
		comboBoxCodeSection.setBounds(800, 33, 150, 20);
		this.add(comboBoxCodeSection);
		
		JLabel lblInfos = new JLabel("<html><i>\"Danc cette fenêtre, vous pouvez lister les softwares qui ne requièrent pas de code d’installation et qui sont utilisés dans une année d’étude choisie.\"</i></html>");
		lblInfos.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblInfos.setBounds(29, 13, 606, 41);
		add(lblInfos);
		comboBoxCodeSection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		
	}
	
	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myParentsearchFrame.dispose();
		}
	}
	
	private void updateTable() {
		try {
			PreparedStatement prepS = myParentsearchFrame.getConn().prepareStatement("SELECT Nom, CodeInstallation, Software.CodeSoftware, a.Annee, a.CodeSection  FROM AnneeEtude a JOIN UtilisationSoftware ON a.IdAnneeEtude = UtilisationSoftware.IdAnneeEtude JOIN Software  ON UtilisationSoftware.CodeSoftware = Software.CodeSoftware  where Software.CodeInstallation is NULL and a.Annee= '"+ comboBoxAnneeEtude.getSelectedItem().toString()+"'AND a.CodeSection ='"+comboBoxCodeSection.getSelectedItem().toString()+"'");			
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
			prepS = myParentsearchFrame.getConn().prepareStatement(sqlInstruction);
			
			toReturn = AccesBDGen.creerListe1Colonne(prepS);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentsearchFrame, "Impossible de charger les années d'études erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}
	
	private Object[] getCodeSection() throws SQLException {
		Object[] toReturn = null;
		PreparedStatement prepS;
		try {
			String sqlInstruction="SELECT DISTINCT CodeSection FROM AnneeEtude ";
			prepS = myParentsearchFrame.getConn().prepareStatement(sqlInstruction);
			
			toReturn = AccesBDGen.creerListe1Colonne(prepS);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(myParentsearchFrame, "Impossible de charger les Codes de Sections erreur: "+e, "Erreur",JOptionPane.WARNING_MESSAGE);
		}
		return toReturn;

	}
	
	
	private void centerJtable(JTable table) {
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.HORIZONTAL);
		for (int i = 0; i < table.getColumnCount(); table.getColumnModel().getColumn(i).setCellRenderer(custom), i++);
	}
}
