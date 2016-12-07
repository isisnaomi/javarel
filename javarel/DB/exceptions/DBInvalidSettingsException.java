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
public class DBInvalidSettingsException extends Exception{
    
  public DBInvalidSettingsException() {
      super(); 
  }
  
  public DBInvalidSettingsException(String message) {
      super(message); 
  }
  
  public DBInvalidSettingsException(Throwable cause) {
       super("DataBaseAccesor: "+cause.getMessage()); 
   }
    
    
}
