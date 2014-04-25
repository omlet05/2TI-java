package defaultt;

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

	public AproposPane() {
		this.setBounds(10,10,527,368); 
		setLayout(null);
		
				
		lblAPropos = new JLabel("<html>\r\n\t<i>\"Ce Projet a été réalisé dans le cadre du cours de programmation orientée objet de deuxièmes TI à l'Henallux durant l'année académique 2013-2014.\"</i>\r\n\t<br>\r\n\t<br>\r\n\t&nbsp;&nbsp;Ce groupe est composé de deux étudiants:\r\n\t<ul>\r\n\t\t<li>Lobet Mathieu</li>\r\n\t\t<br><br><br><br><br><br><br><br>\r\n\t\t<li>Kevin Gouverneur</li>\r\n\t</ul>\r\n</html>\r\n");
		lblAPropos.setVerticalAlignment(SwingConstants.TOP);
		lblAPropos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAPropos.setBounds(33, 26, 451, 299);
		
		//Pictures importation 
				
		iconKevin = new ImageIcon("res/kevin.jpg");
		lblKevin = new JLabel(iconKevin);
		lblKevin.setBounds(100, 234, 118, 123);
		
		iconMath = new ImageIcon("res/Mathieu.jpg");
		lblMath = new JLabel(iconMath);
		lblMath.setBounds(100, 106, 118, 107);
		/*mathIcon = new JLabel(new ImageIcon(mathPic));
		mathIcon.setBounds(192, 195, 102, 98);*/
		
		btnBack = new JButton("Retour");
		btnBack.addActionListener(new Back());
		btnBack.setBounds(452, 336, 65, 23);
		
		
		add(lblKevin);
		add(lblMath);
		add(lblAPropos);
		add(btnBack);

	}
	
	private class Back implements ActionListener{
 		public void actionPerformed(ActionEvent e){
 			MainFrame.redrawNewMain();
 		}
 	}
	
}
