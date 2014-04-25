package defaultt;


import javax.swing.UIManager;

 

public class Principal {

    public static void main(String[] args) {
    	try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            MainFrame w = new MainFrame();
        } catch(Exception e) {
            System.out.println("Impossible de définir le Look & feel par défaut: " + e);
        }
    	
    	
    }
}
