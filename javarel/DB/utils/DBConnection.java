/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB.utils;

import com.mysql.jdbc.Connection;

/**
 *
 * @author Tony
 */
public class DBConnection{
    
    private final Connection conn;

    public DBConnection(Connection connection) {
        this.conn = connection;
    }
    
}
