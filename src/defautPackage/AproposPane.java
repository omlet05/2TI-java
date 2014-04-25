package defautPackage;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.URL;


public class AproposPane extends JPanel {
	private JLabel lblKevin, lblMath, lblAPropos;
	private JButton btnBack;
	private ImageIcon iconKevin, iconMath;
	private MainFrame myParentFrame;

	public AproposPane(MainFrame mainFrame) {
		this.setBounds(10,10,631,396); 
		setLayout(null);
		myParentFrame = mainFrame;
				
		lblAPropos = new JLabel("<html>\r\n\t<i>\"Ce Projet a été réalisé dans le cadre du cours de programmation orientée objet de deuxièmes TI à l'Henallux durant l'année académique 2013-2014.\"</i>\r\n\t<br>\r\n\t<br>\r\n\t&nbsp;&nbsp;Ce groupe est composé de deux étudiants:\r\n\t<ul>\r\n\t\t<li>Lobet Mathieu</li>\r\n\t\t<br><br><br><br><br><br><br><br>\r\n\t\t<li>Kevin Gouverneur</li>\r\n\t</ul>\r\n</html>\r\n");
		lblAPropos.setVerticalAlignment(SwingConstants.TOP);
		lblAPropos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAPropos.setBounds(33, 26, 451, 60);
		
		//Pictures importation 
				
		iconKevin = new ImageIcon("res/kevin.jpg");
		lblKevin = new JLabel(iconKevin);
		lblKevin.setBounds(139, 129, 118, 145);
		
		iconMath = new ImageIcon("res/Mathieu.jpg");
		lblMath = new JLabel(iconMath);
		lblMath.setBounds(319, 129, 130, 145);
		
		
		btnBack = new JButton("Retour");
		btnBack.addActionListener(new Back());
		btnBack.setBounds(553, 361, 65, 23);
		
		
		add(lblKevin);
		add(lblMath);
		add(lblAPropos);
		add(btnBack);
		
		JLabel lblPnom = new JLabel("Kevin Gouverneur               &&               Mathieu Lobet");
		lblPnom.setBounds(139, 285, 310, 14);
		add(lblPnom);

	}
	
	private class Back implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			myParentFrame.redrawNewMain();
 		}
 	}
}
