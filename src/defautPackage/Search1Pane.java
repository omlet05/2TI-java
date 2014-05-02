package defautPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



public class Search1Pane extends JPanel {
	
	private JButton btnBackS1;
	
	
	
	public Search1Pane(Search1Frame search1Frame){
		this.setBounds(10, 10, 631, 396);
		setLayout(null);
		
	
		btnBackS1 = new JButton("Retour");
		btnBackS1.addActionListener(new Back());
		btnBackS1.setBounds(553, 361, 65, 23);
		add(btnBackS1);
	}
	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
