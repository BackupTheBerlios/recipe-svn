/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); 
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at 
 * http://www.mozilla.org/MPL/ 
 *
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF 
 * ANY KIND, either express or implied. See the License for the specific language governing rights and 
 * limitations under the License. 
 *
 * The Original Code is this file. 
 *
 * The Initial Developer of the Original Code is Dieter Lunn. Portions created by 
 * Dieter Lunn are Copyright (C) 2004 Dieter Lunn. All Rights Reserved.
 *
 * IngredientsPanel.java
 *
 * Created on April 22, 2004, 9:27 PM
 */

package ca.coder2000.recipes.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

import ca.coder2000.recipes.Ingredient;

/**
 *
 * @author  Dieter Lunn
 */
public class IngredientsPanel extends JPanel {
    
    private ArrayList list;
    
    /** Creates a new instance of IngredientsPanel */
    public IngredientsPanel() {
        this.setLayout(new GridLayout(0, 2));
    }
    
    public void displayList(ArrayList list) {
        Font fntDefault = new Font(java.util.ResourceBundle.getBundle("recipe").getString("Dialog"), Font.PLAIN, 12);

        for (Iterator i = list.iterator(); i.hasNext();) {
            Ingredient temp = (Ingredient) i.next();
            JLabel amount = new JLabel(temp.getAmount());
            amount.setFont(fntDefault);
            this.add(amount);
            
            JLabel desc = new JLabel(temp.getDesc());
            desc.setFont(fntDefault);
            this.add(desc);
        }
    }
    
    public JPanel getHeaderPanel() {
        JPanel temp = new JPanel();
        JLabel amount = new JLabel(java.util.ResourceBundle.getBundle("recipe").getString("Amount"));
        JLabel desc = new JLabel(java.util.ResourceBundle.getBundle("recipe").getString("Description"));
        
        amount.setBounds(0, 0, 100, 50);
        temp.add(amount, BorderLayout.EAST);
        temp.add(desc);
        
        return temp;
    }
}