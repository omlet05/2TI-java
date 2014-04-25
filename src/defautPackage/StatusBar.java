package defautPackage;
import java.awt.Dimension;
import javax.swing.JLabel;


public class StatusBar extends JLabel {
    
    // Creates a new instance of StatusBar
    public void StatusBar() {
        super.setPreferredSize(new Dimension(100, 16));
        setMessage("");
    }
    
    public void setMessage(String message) {
        setText(" "+message);        
    }        
    
    public void setForeground(String color){
    	setForeground(color);
    }
}