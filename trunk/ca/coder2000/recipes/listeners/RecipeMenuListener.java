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
 * RecipeMenuListener.java
 *
 * Created on April 11, 2004, 9:19 PM
 */

package ca.coder2000.recipes.listeners;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import ca.coder2000.recipes.RecipeFrame;
import ca.coder2000.recipes.filters.RecipeFilter;
import ca.coder2000.recipes.RecipeFile;
import ca.coder2000.common.AboutDialog;

/**
 *
 * @author  Dieter Lunn
 */
public class RecipeMenuListener implements ActionListener {
    
    private RecipeFrame frame;
    final JFileChooser fc = new JFileChooser();
    
    /** Creates a new instance of RecipeMenuListener */
    public RecipeMenuListener(RecipeFrame frame) {
        this.frame = frame;
    }
    
    public void actionPerformed(java.awt.event.ActionEvent actionEvent) {
        if (actionEvent.getSource().equals(frame.menuFileExit)) {
            System.exit(0);
        } else if (actionEvent.getSource().equals(frame.menuFileOpen)) {
            fc.addChoosableFileFilter(new RecipeFilter());
            
            int returnVal = fc.showOpenDialog(frame);
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                RecipeFile r = new RecipeFile(fc.getSelectedFile());
                if (r.valid)
                    frame.displayRecipe(r);
            }
        } else if (actionEvent.getSource().equals(frame.menuHelpAbout)) {
            ArrayList devs = new ArrayList();
            devs.add("Dieter Lunn");
            devs.add("Michael Scholz");
            devs.add("Patrik Johansson");
            devs.add("Denis Ustimenko");
            devs.add("swaungcenter");
            devs.add("javagio");
            devs.add("Trimbitas Sorin");
            AboutDialog about = new AboutDialog(frame, devs);
            about.setVisible(true);
        }
    }  
}
