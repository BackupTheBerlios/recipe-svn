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
 * Recipes.java
 *
 * Created on April 11, 2004, 6:47 PM
 */

import ca.coder2000.recipes.RecipeFrame;
import ca.coder2000.recipes.RunOnce;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import java.io.File;
import com.digitprop.tonic.TonicLookAndFeel;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author  Dieter Lunn
 */
public class Recipes {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(new TonicLookAndFeel());
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch(Exception e) {   
        }
        
        RecipeFrame frame = new RecipeFrame();

        frame.setDefaultLookAndFeelDecorated(true);

        File runOnce = new File("data\\runonce.dat");
        if (!runOnce.exists()) {
            RunOnce once = new RunOnce(frame);
            once.go();
        } else {
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);            
        }
    }
}