package defautPackage;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MainPane extends JPanel {
	private JLabel motd;

	public MainPane() {
		this.setBounds(10, 10, 374, 338);
		setLayout(null);

		// welcome message
		motd = new JLabel(
				"<html><br><br><br><br><b>&nbsp;&nbsp;&nbsp;&nbsp;<u>Bienvenue</u></b><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...<li><ul>Ce programme...</ul><ul>Ce programme...</ul><ul>Ce programme...</ul></li></html>",
				SwingConstants.LEFT);
		motd.setBounds(0, 0, 374, 338);
		motd.setVerticalAlignment(SwingConstants.TOP);

		add(motd);
	}
}