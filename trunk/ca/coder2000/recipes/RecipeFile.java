/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); 
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at 
 * http://www.mozilla.org/MPL/ 
 *
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF 
 * ANY KIND, either express or implied. See the License for the specific language governing rights and 
 * limitations under the License. 
 *
 * The Original Code is the RecipeFile class. 
 *
 * The Initial Developer of the Original Code is Dieter Lunn. Portions created by 
 * Dieter Lunn are Copyright (C) 2004 Dieter Lunn. All Rights Reserved.
 *
 * RecipeFile.java
 *
 * Created on April 11, 2004, 9:54 PM
 */

package ca.coder2000.recipes;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.Node;
import org.dom4j.Element;
import org.dom4j.Attribute;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.stream.StreamSource;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.dom4j.io.XMLWriter;
import org.dom4j.io.OutputFormat;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

import ca.coder2000.recipes.handlers.RecipeErrorHandler;

/**
 * This class represents one recipe.
 * @author Dieter Lunn
 */
public class RecipeFile {
    
    private static Document doc;
    private Node temp;
    /**
     * Is the recipe valid or not
     */    
    public boolean valid;
    
    /**
     * Creates a new instance of RecipeFile
     * @param file the file we want this class to load and manage.
     */
    public RecipeFile(File file) {
        SAXReader reader = new SAXReader(true);
        reader.setValidation(true);
        
        try {
            reader.setFeature("http://xml.org/sax/features/validation", true);
            reader.setFeature("http://apache.org/xml/features/validation/schema", true);
            reader.setProperty("http://apache.org/xml/properties/schema/external-noNamespaceSchemaLocation", "schemas/recipe.xsd");
            reader.setErrorHandler(new RecipeErrorHandler());
            doc = reader.read(file);
            checkVersion();
        } catch (DocumentException de) {
            JOptionPane.showMessageDialog(null, de.getMessage(), java.util.ResourceBundle.getBundle("recipe").getString("Error_Parsing_Recipe"), JOptionPane.ERROR_MESSAGE);
        } catch (SAXException sxe) {
            JOptionPane.showMessageDialog(null, sxe.getMessage(), java.util.ResourceBundle.getBundle("recipe").getString("Error_Parsing_Recipe"), JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), java.util.ResourceBundle.getBundle("recipe").getString("Error_Parsing_Recipe"), JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Gets the specified sub-tag of the info tag.
     * @param section The sub-tag to return.
     * @return The value of the requested sub-tag.
     */    
    public String getInfo(String section) {
        temp = doc.selectSingleNode("//Info/".concat(section));
        return temp.getStringValue();
    }
    
    /**
     * Gets the temperature from the recipe.
     * @return The temperature in Farenheit or Celcius.
     */    
    public String getTemperature() {
        String value;
        StringBuffer tempString;
        Boolean celcius;
        
        temp = doc.selectSingleNode("//Info/Temperature/Value");
        value = temp.getStringValue();
        
        tempString = new StringBuffer(value);
        
        temp = doc.selectSingleNode("//Info/Temperature/Celcius");
        celcius = Boolean.valueOf(temp.getStringValue());
        if (celcius.booleanValue()) {
            tempString.append("\u2103");
        } else {
            tempString.append("\u2109");
        }
        
        return tempString.toString();
    }
    
    /**
     * Gets the ingredients list.
     * @return The list of ingredients in the recipe.
     */    
    public ArrayList getIngredients() {
        ArrayList list = new ArrayList();
        List lst = doc.selectNodes("//Ingredients/Ingredient");
        Iterator i = lst.iterator();
        
        while ( i.hasNext()) {
            Node ing = (Node) i.next();
            Node name = ing.selectSingleNode("//Name");
            Node amount = ing.selectSingleNode("//Amount");
            Node measure = ing.selectSingleNode("//Measure");
            Ingredient j = new Ingredient(name.getStringValue(), amount.getStringValue(), measure.getStringValue());
            list.add(j);
        }
        
        return list;
    }
    
    /**
     * Checks the version of the recipe file.
     * @throws Exception If the version of the recipe file is wrong.
     */    
    public void checkVersion() throws Exception {
        Element root = doc.getRootElement();
        Attribute version = root.attribute("version");
        
        if (Double.valueOf(version.getStringValue()).doubleValue() != 0.1) {
            valid = false;
            throw new Exception(java.util.ResourceBundle.getBundle("recipe").getString("Invalid_Recipe_File_Version"));
        }
        
        valid = true;
    }
    
    /**
     * Returns formatted HTML from the xml file.
     * @param stylesheet The stylesheet to transform against.
     * @return The HTML to be used.
     */    
    public Document getHTML(File stylesheet) {
        Transformer transformer;
        TransformerFactory factory = TransformerFactory.newInstance();
        
        DocumentSource source = new DocumentSource(doc);
        DocumentResult result = new DocumentResult();
        
        try {
            transformer = factory.newTransformer(new StreamSource(stylesheet));
            transformer.transform(source, result);
            // Hard coded path MUST remove
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(new FileWriter("k:/recipe/recipe.tmp"), format);
            writer.write(result.getDocument());
        } catch (TransformerException te) {
        } catch (IOException ioe) {}
        
        
        return result.getDocument();
    }
}