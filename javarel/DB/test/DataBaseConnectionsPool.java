/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.test;

import DB.DataBaseAccesor;
import exceptions.DBInvalidSettingsException;
import exceptions.DBFileException;
import exceptions.DBQueryException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tony
 */
public class DataBaseConnectionsPool {
    
    public void updateConnections(){
       
            System.out.println("metodo updateConexions");
            
    }
    
    
      public static void main(String args[]){
          
        DataBaseAccesor d = new DataBaseAccesor(new DataBaseConnectionsPool());
        Connection con = null;
       
        try {
            
            con = d.getConnection();
            
        } catch (DBQueryException ex) {
            
            System.out.println(ex);
            
        } catch (DBInvalidSettingsException ex) {
            
        } catch (DBFileException ex) {
        }
        
          System.out.println(con);
        
    }
}
