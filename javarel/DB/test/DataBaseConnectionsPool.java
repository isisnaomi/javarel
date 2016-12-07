/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB.test;

import javarel.DB.DataBaseAccessor;
import javarel.DB.exceptions.DBInvalidSettingsException;
import javarel.DB.exceptions.DBFileException;
import javarel.DB.exceptions.DBQueryException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javarel.DB.utils.DBConnection;


/**
 *
 * @author Tony
 */
public class DataBaseConnectionsPool {
    
    public void updateConnections(){
       
            System.out.println("metodo updateConexions");
            
    }
    
    
      public static void main(String args[]){
          
        DataBaseAccessor d = new DataBaseAccessor(new DataBaseConnectionsPool());
        DBConnection con = null;
                ResultSet res = null;
       
        try {
            
            con = d.getConnection();
            res = con.query("SELECT * FROM usuarios");
            
        } catch (DBQueryException | DBInvalidSettingsException | DBFileException ex) {
            
            System.out.println(ex);
            
        }
        
          System.out.println(con);
        try {
            System.out.println(res.getArray("Nombre"));
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseConnectionsPool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
