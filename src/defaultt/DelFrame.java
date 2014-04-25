package defaultt;


import java.awt.Container;

import javax.swing.*;




public class DelFrame extends JFrame {
	
	private Container cont;
	private DelPane panel;
	
	public DelFrame() {
		setTitle("Supression d'un software.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(792, 526);
		setResizable(false);
		setLocationRelativeTo(null);
		
		panel = new DelPane();
		
		
		cont = getContentPane(); 
		cont.setLayout(null); 
		cont.add (panel);
		
		setVisible(true);
	}
	
	
	

	

	
	
    
	
		
	
}
