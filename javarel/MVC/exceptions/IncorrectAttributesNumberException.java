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
public class IncorrectAttributesNumberException extends Exception{
    
    public IncorrectAttributesNumberException(){
        super("Callservice needs to be defined with only 3 attributes (name, controller, method).");
    }
}
