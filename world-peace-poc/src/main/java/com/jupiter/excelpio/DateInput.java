package com.jupiter.excelpio;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
 

@SuppressWarnings("serial")
public class DateInput extends JTextField { 
      private static int inputLengh = 0;
 
    public DateInput(int cols) { 
        super(cols); 
    }   
    protected Document createDefaultModel() { 
        return new UpperCaseDocument(); 
    } 

    static class UpperCaseDocument extends PlainDocument { 

    	
        public void insertString(int offs, String str, AttributeSet a) 
            throws BadLocationException {
 
            if (str == null) { 
                return; 
            } 
            char[] upper = str.toCharArray(); 
            String filtered = ""; 
            for (int i = 0; i < upper.length; i++) {
                if (inputLengh < 8 && Character.isDigit(Character.codePointAt(upper, i))){ 
                    filtered += upper[i]; 
                    inputLengh ++;
                } 
            } 
            super.insertString(offs, filtered, a); 
        } 
        
        protected void removeUpdate(DefaultDocumentEvent chng) {
        	super.removeUpdate(chng);
        	inputLengh -= chng.getLength();
            }
    } 
} 