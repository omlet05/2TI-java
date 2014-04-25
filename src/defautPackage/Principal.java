package defautPackage;


import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;



public class Principal {

    public static void main(String[] args) {
    	try {
            //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
            UIManager.put("RootPane.setupButtonVisible", false);
            /**The key be related to font of UIManager's UI */
            
    		new MainFrame();
        } catch(Exception e) {
            System.out.println("Impossible de définir le Look & feel par défaut: " + e);
        }
    	
    	
    	
    }
}
