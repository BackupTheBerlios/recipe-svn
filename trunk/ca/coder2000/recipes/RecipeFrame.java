/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); 
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at 
 * http://www.mozilla.org/MPL/ 
 *
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF 
 * ANY KIND, either express or implied. See the License for the specific language governing rights and 
 * limitations under the License. 
 *
 * The Original Code is the main window of the application. 
 *
 * The Initial Developer of the Original Code is Dieter Lunn. Portions created by 
 * Dieter Lunn are Copyright (C) 2004 Dieter Lunn. All Rights Reserved.
 *
 * RecipeFrame.java
 *
 * Created on April 11, 2004, 6:49 PM
 */

package ca.coder2000.recipes;

import ca.coder2000.recipes.listeners.RecipeMenuListener;
import ca.coder2000.recipes.panels.InfoPanel;
import ca.coder2000.recipes.panels.IngredientsPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.dom4j.Document;

import java.sql.*;

/**
 * This is the main frame of the application.
 * @author Dieter Lunn
 */
public class RecipeFrame extends JFrame{
    
    /**
     * the file>open menu item.  public cause its needed by the listener.
     */    
    public JMenuItem menuFileOpen = new JMenuItem();
    /**
     * Imports a new recipe to the database
     */
    public JMenuItem menuFileImport = new JMenuItem();
    /**
     * the file>exit menu item. public cause it is needed by the listener.
     */    
    public JMenuItem menuFileExit = new JMenuItem();
    /**
     * displays the about window of the application.
     */
    public JMenuItem menuHelpAbout = new JMenuItem();
    
    private InfoPanel info;
    private IngredientsPanel ingpanel;
    
    /**
     * the database connection used by the application
     */
    public Connection conn;
    
    /** Creates a new instance of RecipeFrame */
    public RecipeFrame() {
        try {
            conn = DriverManager.getConnection("jdbc:derby:data/recipes");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu();
        JMenu menuHelp = new JMenu();
        
        menuFile.setText(java.util.ResourceBundle.getBundle("recipe").getString("File"));
        menuFileOpen.setText(java.util.ResourceBundle.getBundle("recipe").getString("Open"));
        menuFileImport.setText("Import");
        menuFileExit.setText(java.util.ResourceBundle.getBundle("recipe").getString("Exit"));
        
        menuFileOpen.addActionListener(new RecipeMenuListener(this));
        menuFile.add(menuFileOpen);
        
        menuFile.add(menuFileImport);
        
        menuFileExit.addActionListener(new RecipeMenuListener(this));
        menuFile.add(menuFileExit);
        menuBar.add(menuFile);
        
        menuHelp.setText(java.util.ResourceBundle.getBundle("recipe").getString("Help"));
        menuHelpAbout.setText(java.util.ResourceBundle.getBundle("recipe").getString("About"));
        menuHelpAbout.addActionListener(new RecipeMenuListener(this));
        menuHelp.add(menuHelpAbout);
        menuBar.add(menuHelp);
        
        setTitle(java.util.ResourceBundle.getBundle("recipe").getString("AppTitle"));
        setJMenuBar(menuBar);
        setSize(new Dimension(400, 400));
        
        JScrollPane ing = new JScrollPane();
        this.getContentPane().add(ing, BorderLayout.CENTER);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
    }
    
    /**
     * displays the recipe.  is called from the event handler.
     * @param fldRecId Id from the database of the recipe to display.
     */    
    public void displayRecipe(int fldRecId) {
        
    }
}
