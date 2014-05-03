package defautPackage;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.mysql.jdbc.Messages;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UpdateFrame extends JFrame {
	private Container cont;
	private UpdateTablePane panel2;
	private MainFrame MyMainFrame;
	private Connection conn;
	private JTabbedPane tabbedPane;

	public UpdateFrame(MainFrame f) {
		setTitle("Modification d'une Installation.");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(749, 675);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// icon
		Icon iconListing = new ImageIcon("res/tabUpdate.png");
		
		
		MyMainFrame = f;
		setConn(f.getConn());

		cont = getContentPane();
		
		
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(10, 10, 700, 575);
		getContentPane().add(tabbedPane);
		
		panel2 = new UpdateTablePane(UpdateFrame.this); 
		tabbedPane.addTab("Listing", iconListing, panel2, null);
		
		
		cont.add(tabbedPane);
		setVisible(true);
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public Connection getConn() {
		return conn;
	}
	
	public void updateTabpane(){
    	panel2.updateTable();
    }

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public void newtab(int index){
		JPanel newPane;
		String tabName = "Modification de l'installation "+index+"     ";
		// icon
		Icon iconUpdate = new ImageIcon("res/modifier-icon.png");
		
		newPane = new UpdateFormPane(UpdateFrame.this, index); 
		tabbedPane.addTab(tabName, iconUpdate, newPane, null);
		
		
		//close button
		int index2 = tabbedPane.indexOfTab(tabName);
		JPanel pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(tabName);
		JButton btnClose = new JButton(new ImageIcon("res/Delete.png"));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;

		pnlTab.add(lblTitle, gbc);

		gbc.gridx++;
		gbc.weightx = 0;
		pnlTab.add(btnClose, gbc);

		tabbedPane.setTabComponentAt(index2, pnlTab);

		btnClose.addActionListener(new MyCloseActionHandler(tabName));
		
	}
	
	
	
	private class MyCloseActionHandler implements ActionListener {

	    private String tabName;

	    public MyCloseActionHandler(String tabName) {
	        this.tabName = tabName;
	    }

	    public String getTabName() {
	        return tabName;
	    }
	    

	    public void actionPerformed(ActionEvent evt) {

	        int index = tabbedPane.indexOfTab(getTabName());
	        if (index >= 0) {
	        	/* remove tab */
	        	tabbedPane.removeTabAt(index);
	        }

	    }
	    
	    

	}   

}
