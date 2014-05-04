package defautPackage;

import java.awt.Container;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {
	private Container cont;
	private LoginPane panel;
	private MainFrame mainF;
	private Connection conn;

	public LoginFrame(MainFrame f) {
		super("Connexion");
		setIconImage(new javax.swing.ImageIcon("res/icons/login-icon.png").getImage());
		setSize(258, 215);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		mainF = f;
		setConn(mainF.getConn());

		panel = new LoginPane(mainF, LoginFrame.this);
		panel.setLocation(10, 11);

		// container
		cont = getContentPane();
		cont.setLayout(null);
		cont.add(panel);

		setVisible(true);
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
