package defautPackage;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class UpdateFrame extends JFrame {
	private Container cont;
	private UpdateTablePane panel2;
	private MainFrame MyMainFrame;
	private Connection conn;
	private JTabbedPane tabbedPane;
	private int i;

	public UpdateFrame(MainFrame f) {
		setTitle("Modification d'une Installation.");
		setIconImage(new javax.swing.ImageIcon("res/icons/edit-icon.png").getImage());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(749, 675);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		/* i to prevent duplicate tab */
		i = 1;
		
		// icon
		Icon iconListing = new ImageIcon("res/tabUpdate.png");
		
		
		MyMainFrame = f;
		conn = MyMainFrame.getConn();
		

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
	

	
	public void newtab(int index){
		JPanel newPane;
		String tabName = i+". Modification de l'installation: "+index+"     ";
		
		
		//tab icon
		Icon iconUpdate = new ImageIcon("res/modifier-icon.png");
		
		newPane = new UpdateFormPane(UpdateFrame.this, index); 
		tabbedPane.addTab(tabName, iconUpdate, newPane, null);
		
		
		//close button on the tab
		int index2 = tabbedPane.indexOfTab(tabName);
		JPanel pnlTab;
		
		pnlTab = new JPanel(new GridBagLayout());
		pnlTab.setOpaque(false);
		JLabel lblTitle = new JLabel(tabName);
		JButton btnClose = new JButton(new ImageIcon("res/Delete.png"));
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setContentAreaFilled(false);

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
		i++;
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
	        if (index >= 0){
	        	tabbedPane.removeTabAt(index);
	        	i--;
	        }
	        	
	        /* remove tab */
	        

	    }
	    
	    

	}   

}
