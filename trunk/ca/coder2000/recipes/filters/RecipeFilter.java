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
 * RecipeFilter.java
 *
 * Created on April 11, 2004, 9:30 PM
 */

package ca.coder2000.recipes.filters;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 *
 * @author  Dieter Lunn
 */
public class RecipeFilter extends FileFilter {
    
    /** Creates a new instance of RecipeFilter */
    public RecipeFilter() {
    }
    
    public boolean accept(java.io.File file) {
        if (file.isDirectory()) {
            return true;
        }
        
        String extension = getExtension(file);
        
        if (extension != null) {
            if (extension.equals("rcp")) {
                return true;
            } else {
                return false;
            }
        }
        
        return false;
    }
    
    public String getDescription() {
        return "Recipes";
    }
    
    private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        
        return ext;
    }
}
