/*
 * The contents of this file are subject to the Mozilla Public License Version 1.1 (the "License"); 
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at 
 * http://www.mozilla.org/MPL/ 
 *
 * Software distributed under the License is distributed on an "AS IS" basis, WITHOUT WARRANTY OF 
 * ANY KIND, either express or implied. See the License for the specific language governing rights and 
 * limitations under the License. 
 *
 * The Original Code is the Ingredient class. 
 *
 * The Initial Developer of the Original Code is Dieter Lunn. Portions created by 
 * Dieter Lunn are Copyright (C) 2004 Dieter Lunn. All Rights Reserved.
 *
 * Ingredient.java
 *
 * Created on April 22, 2004, 8:40 PM
 */

package ca.coder2000.recipes;

/**
 *
 * @author  Dieter Lunn
 */
public class Ingredient {
    
    private String strName;
    private String strAmount;
    private String strMeasure;
    
    /** Creates a new instance of Ingredient */
    public Ingredient() {
    }
    
    public Ingredient(String name, String amount, String measure) {
        strName = name;
        strAmount = amount;
        strMeasure = measure;
    }
    
    public void setName(String name) {
        strName = name;
    }
    
    public void setAmount(String amount) {
        strAmount = amount;
    }
    
    public void setMeasure(String measure) {
        strMeasure = measure;
    }
    
    public String getAmount() {
        StringBuffer temp = new StringBuffer(strAmount);
        temp.append(" ");
        temp.append(strMeasure);
        
        return temp.toString();
    }
    
    public String getDesc() {
        return strName;
    }
}