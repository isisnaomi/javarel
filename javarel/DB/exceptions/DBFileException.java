/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author Tony
 */
public class DBFileException extends Exception{
     
  public DBFileException( Throwable cause ) {
      
       super( "DataBaseAccesor: " + cause.getMessage() ); 
       
  }
    
}
