/*
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
 *
 * @author Dieter Lunn
 */
public class RunOnce extends JFrame implements Runnable {
    Thread sqlThread = null;
    JLabel status = new JLabel();
    JProgressBar progress = new JProgressBar();
    JFrame parent;
    
    /** Creates a new instance of RunOnce */
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
    
    public void go() {
        setVisible(true);
        sqlThread = new Thread(this, "SQL");
        sqlThread.start();
    }
    
    public void run() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:derby:data/recipes;create=true");
            progress.setValue(1);
            status.setText("Creating Tables...");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE tblRecipes (fldId INTEGER NOT NULL, fldAuthor VARCHAR(40) NOT NULL, fldName VARCHAR(100) NOT NULL, PRIMARY KEY (fldId))");
            progress.setValue(2);
            stmt.execute("CREATE TABLE tblIngredients (fldId INTEGER NOT NULL, fldName VARCHAR(40) NOT NULL, PRIMARY KEY (fldId))");
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