/*
 * RecipeOpen.java
 *
 * Created on January 28, 2005, 9:18 AM
 */

package ca.coder2000.recipes;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.Dimension;

import java.sql.*;

/**
 *
 * @author Dieter Lunn
 */
public class RecipeOpen extends JDialog implements ActionListener {
    private Connection conn;
    private Statement stmt;
    private ResultSet recipes;
    private String[] columns = {"Id", "Name", "Author"};
    private Object[][] data = {{" ", " ", " "}};
    private JTable table;
    private int returnValue;
    
    /** Creates a new instance of RecipeOpen */
    public RecipeOpen(Connection conn) {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        this.conn = conn;
        
        JScrollPane scroll = new JScrollPane();
        table = new JTable(data, columns);
        scroll.add(table);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        getContentPane().add(scroll, c);
        
        JButton btnOpen = new JButton("Open");
        btnOpen.addActionListener(this);
        c.gridwidth = 1;
        c.gridx = 1;
        c.gridy = 1;
        getContentPane().add(btnOpen, c);
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);
        c.gridx = 2;
        c.gridy = 1;
        getContentPane().add(btnCancel, c);
        
        setTitle("Open Recipe");
        setSize(new Dimension(400, 300));
        setModal(true);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
    }
    
    public void showRecipeOpen() {
        try {
            stmt = conn.createStatement();
            recipes = stmt.executeQuery("SELECT fldId, fldName, fldAuthor FROM tblRecipes");
        } catch (Exception e) {
            e.printStackTrace();
        }
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
}