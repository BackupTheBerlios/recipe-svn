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
 * InfoPanel.java
 *
 * Created on April 12, 2004, 12:52 AM
 */

package ca.coder2000.recipes.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * InfoPanel is the panel used to display the information like author and temperature
 * etc...
 * @author Dieter Lunn
 */
public class InfoPanel extends JPanel {
    
    private JLabel lblTitle;
    private JLabel lblAuthor;
    private JLabel lblTemperature;
    private JLabel lblDescription;
    private JLabel lblCookTime;
    private JLabel lblPrepTime;
    private JLabel lblQuantity;
    private JLabel lblTemp;
    private Font fntDefault;
    
    /** Creates a new instance of InfoPanel */
    public InfoPanel() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        
        fntDefault = new Font(java.util.ResourceBundle.getBundle("recipe").getString("Dialog"), Font.PLAIN, 12);
        
        lblTemp = new JLabel(java.util.ResourceBundle.getBundle("recipe").getString("Title"));
        c.gridx = 0;
        c.gridy = 0;
        this.add(lblTemp, c);
        
        lblTitle = new JLabel();
        lblTitle.setFont(fntDefault);
        c.gridx = 1;
        c.gridy = 0;
        this.add(lblTitle, c);
        
        lblTemp = new JLabel(java.util.ResourceBundle.getBundle("recipe").getString("Author"));
        c.gridx = 2;
        c.gridy = 0;
        this.add(lblTemp, c);
        
        lblAuthor = new JLabel();
        lblAuthor.setFont(fntDefault);
        c.gridx = 3;
        c.gridy = 0;
        this.add(lblAuthor, c);
        
        lblTemp = new JLabel(java.util.ResourceBundle.getBundle("recipe").getString("Description"));
        c.gridwidth = 4;
        c.gridx = 0;
        c.gridy = 1;
        this.add(lblTemp, c);
        
        lblDescription = new JLabel();
        lblDescription.setFont(fntDefault);
        c.gridx = 0;
        c.gridy = 2;
        this.add(lblDescription, c);
        
        lblTemp = new JLabel(java.util.ResourceBundle.getBundle("recipe").getString("Preheat_oven_to"));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        this.add(lblTemp, c);
        
        lblTemperature = new JLabel();
        lblTemperature.setFont(fntDefault);
        c.gridx = 1;
        c.gridy = 3;
        this.add(lblTemperature, c);
    }
    
    /**
     * Sets the title of the recipe.
     * @param title The title of the recipe
     */    
    public void setTitle(String title) {
        lblTitle.setText(title);
    }
    
    /**
     * Sets the author of the recipe.
     * @param author the author of the recipe to be set.
     */    
    public void setAuthor(String author) {
        lblAuthor.setText(author);
    }
    
    /**
     * sets the description of the recipe
     * @param desc the description to set.
     */    
    public void setDescription(String desc) {
        lblDescription.setText(desc);
    }
    
    /**
     * sets the temperature of the recipe
     * @param temp the text representing the temperature to set.
     */    
    public void setTemperature(String temp) {
        lblTemperature.setText(temp);
    }
}