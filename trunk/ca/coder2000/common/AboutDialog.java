/*
 * AboutDialog.java
 *
 * Created on May 3, 2004, 8:31 PM
 */

package ca.coder2000.common;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.util.List;
import java.util.Iterator;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;
import javax.swing.UIManager;

/**
 *
 * @author  Dieter Lunn
 */
public class AboutDialog extends JDialog {
    
    /** Creates a new instance of AboutDialog */
    public AboutDialog(JFrame parent, List devs) {
        super(parent, true);
        StringBuffer dev = new StringBuffer();
        JTextArea d = new JTextArea();
        
        this.setLayout(new GridLayout(0,2));
        
        for (Iterator i = devs.iterator(); i.hasNext();) {
            String s = (String) i.next();
            dev.append(s);
            dev.append(", ");
        }
        
        d.setText(dev.toString());
        textAreaLikeLabel(d);
        
        this.setTitle("About");
        
        this.getContentPane().add(new JLabel("Developers:"));
        this.getContentPane().add(d);
    }
    
    private void textAreaLikeLabel(JTextArea area) {
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        textComponentLikeLabel(area);
    }
    
    private void textComponentLikeLabel(JTextComponent text) {
        text.setEditable(false);
        
        text.setForeground((Color) UIManager.get("Label.foreground"));
        text.setBackground((Color) UIManager.get("Label.background"));
        text.setBorder(null);
    }
}