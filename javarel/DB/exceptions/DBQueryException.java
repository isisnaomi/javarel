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
public class DBQueryException extends Exception{
         
   public DBQueryException( Throwable cause ) {
       
       super( "DataBaseAccesor Querys: "+cause.getMessage(), cause ); 
       
   }
  
}
