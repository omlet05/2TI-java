package defautPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AproposPane extends JPanel {
	private JLabel lblKevin, lblMath, lblAPropos;
	private JButton btnBack;
	private ImageIcon iconKevin, iconMath;
	private MainFrame myParentFrame;

	public AproposPane(MainFrame mainFrame) {
		this.setBounds(10, 10, 597, 335);
		setLayout(null);
		myParentFrame = mainFrame;

		lblAPropos = new JLabel(
				"<html>\r\n\t<i>\"Ce Projet a été réalisé dans le cadre du cours de programmation orientée objet de deuxièmes TI à l'Henallux durant l'année académique 2013-2014.\"</i>\r\n\t<br>\r\n\t<br>\r\n\t&nbsp;&nbsp;Ce groupe est composé de deux étudiants:\r\n\t<ul>\r\n\t\t<li>Lobet Mathieu</li>\r\n\t\t<br><br><br><br><br><br><br><br>\r\n\t\t<li>Kevin Gouverneur</li>\r\n\t</ul>\r\n</html>\r\n");
		lblAPropos.setVerticalAlignment(SwingConstants.TOP);
		lblAPropos.setHorizontalAlignment(SwingConstants.LEFT);
		lblAPropos.setBounds(33, 26, 451, 60);
		add(lblAPropos);

		// Pictures importation

		iconKevin = new ImageIcon("res/Kevin.jpg");
		lblKevin = new JLabel(iconKevin);
		lblKevin.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1,
				Color.black));
		lblKevin.setBounds(139, 143, 118, 129);
		add(lblKevin);

		iconMath = new ImageIcon("res/Mathieu.jpg");
		lblMath = new JLabel(iconMath);
		lblMath.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1,
				Color.black));
		lblMath.setBounds(319, 143, 130, 129);
		add(lblMath);

		btnBack = new JButton("Retour");
		btnBack.addActionListener(new Back());
		btnBack.setBounds(553, 361, 65, 23);
		add(btnBack);

		JLabel lblPnom = new JLabel(
				"Kevin Gouverneur               &&               Mathieu Lobet");
		lblPnom.setBounds(86, 285, 428, 14);
		add(lblPnom);

	}

	private class Back implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myParentFrame.redrawNewMain();
		}
	}
}
