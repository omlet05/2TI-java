package defautPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class Search1Pane extends JPanel {
	private MainFrame myFenetre1Pane;
	private JButton btnBackS1;
	
	
	
	public Search1Pane(MainFrame mainF){
		this.setBounds(10, 10, 631, 396);
		setLayout(null);
		myFenetre1Pane = mainF;
	
		btnBackS1 = new JButton("Retour");
		btnBackS1.addActionListener(new Back());
		btnBackS1.setBounds(553, 361, 65, 23);
		add(btnBackS1);
	}
	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myFenetre1Pane.redrawNewMain();
		}
	}
}
