/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.security.exceptions;

/**
 *
 * @author Viqtor
 */
public class MaximumLimitOfAttemps extends Exception{
    
    
    public MaximumLimitOfAttemps(){
        super("Maximun of attemps on login");
    }


    
}
