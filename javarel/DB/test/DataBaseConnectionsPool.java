/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB.test;

import javarel.DB.DataBaseAccesor;
import javarel.DB.exceptions.DBInvalidSettingsException;
import javarel.DB.exceptions.DBFileException;
import javarel.DB.exceptions.DBQueryException;
import java.sql.Connection;


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
