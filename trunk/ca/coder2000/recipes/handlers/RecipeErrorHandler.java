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
 * RecipeErrorHandler.java
 *
 * Created on April 12, 2004, 12:22 AM
 */

package ca.coder2000.recipes.handlers;

import org.xml.sax.ErrorHandler;

/**
 *
 * @author  Dieter Lunn
 */
public class RecipeErrorHandler implements ErrorHandler {
    
    /** Creates a new instance of RecipeErrorHandler */
    public RecipeErrorHandler() {
    }
    
    public void error(org.xml.sax.SAXParseException exception) throws org.xml.sax.SAXException {
    }
    
    public void fatalError(org.xml.sax.SAXParseException exception) throws org.xml.sax.SAXException {
    }
    
    public void warning(org.xml.sax.SAXParseException exception) throws org.xml.sax.SAXException {
    }
    
}
