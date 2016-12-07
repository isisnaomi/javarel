/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB.exceptions;

/**
 *
 * @author Tony
 */
public class DBConectionException extends Exception{
    
    public DBConectionException( Throwable cause ) {
       
       super( "DataBaseAccesor Connections: "+cause.getMessage(), cause ); 
       
   }
}
