/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc_exceptions;

/**
 *
 * @author RIKI
 */
public class IncorrectAttributeNameException extends Exception{
    
    public IncorrectAttributeNameException(String attributeName){
        super("Incorrect attribute name: " + attributeName);
    }
}
