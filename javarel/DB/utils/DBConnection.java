/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB.utils;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javarel.DB.exceptions.DBQueryException;


/**
 *
 * @author Tony
 */
public class DBConnection{
    
    private final Connection JDBCConnection;

    public DBConnection(Connection connection) {
        this.JDBCConnection = connection;
        
    }
    
    public ResultSet query(String query) throws DBQueryException{
        
        ResultSet result;
        
        try {
            
            Statement stmt = null;
            stmt = (Statement) JDBCConnection.createStatement();
            result = stmt.executeQuery(query);
            
            
        } catch (SQLException ex) {
            
            throw new DBQueryException(ex);
            
        }
        
        return result;
    }
    
    public void disconnect(){
        
        try {
            JDBCConnection.close();
        } catch (SQLException ex) {
            
        }
                
    }
    
}
