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
 * Dieter Lunn are Copyright (C) 2005 Dieter Lunn. All Rights Reserved.
 *
 * RunOnce.java
 *
 * Created on January 26, 2005, 6:32 PM
 */

package ca.coder2000.recipes;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Runs once at the startup of the application to create the necessary database and tables.
 * @author Dieter Lunn
 */
public class RunOnce extends JFrame implements Runnable {
    private Thread sqlThread = null;
    private JLabel status = new JLabel();
    private JProgressBar progress = new JProgressBar();
    private JFrame parent;
    
    /**
     * Creates a new instance of RunOnce
     * @param parent The parent window of the setup class
     */
    public RunOnce(JFrame parent) {
        setTitle("First Run Setup");
        status.setText("Creating database...");
        progress.setMaximum(10);
        progress.setValue(0);
        getContentPane().add(status, BorderLayout.NORTH);
        getContentPane().add(progress, BorderLayout.SOUTH);
        setDefaultLookAndFeelDecorated(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(300, 75));
        setAlwaysOnTop(true);
        this.parent = parent;
    }
    
    /**
     * Used to start the setup.
     */
    public void go() {
        setVisible(true);
        sqlThread = new Thread(this, "SQL");
        sqlThread.start();
    }
    
    /**
     * thread run method that creates the database and tables
     */
    public void run() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:data/recipes;create=true");
            progress.setValue(1);
            status.setText("Creating Tables...");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE tblRecipes (fldId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, fldAuthor VARCHAR(40) NOT NULL, fldName VARCHAR(100) NOT NULL, PRIMARY KEY (fldId))");
            progress.setValue(2);
            stmt.execute("CREATE TABLE tblIngredients (fldId INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY, fldName VARCHAR(40) NOT NULL, PRIMARY KEY (fldId))");
            progress.setValue(3);
            stmt.execute("CREATE TABLE tblIngInRec (fldRecId INTEGER NOT NULL, fldIngId INTEGER NOT NULL, fldAmount INTEGER NOT NULL, PRIMARY KEY (fldIngId, fldRecId))");
            progress.setValue(4);
            stmt.execute("CREATE TABLE tblSystem (fldDbVersion INTEGER NOT NULL)");
            progress.setValue(5);
            stmt.execute("INSERT INTO tblSystem (fldDbVersion) VALUES (1)");
            progress.setValue(6);
            File once = new File("data\\runonce.dat");
            once.createNewFile();
            progress.setValue(10);
            setVisible(false);
            parent.setLocationRelativeTo(null);
            parent.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}