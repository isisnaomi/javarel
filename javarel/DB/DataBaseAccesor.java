/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB;

import javarel.DB.test.DataBaseConnectionsPool;

import javarel.DB.exceptions.DBInvalidSettingsException;
import javarel.DB.exceptions.DBFileException;
import javarel.DB.exceptions.DBQueryException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author David Hernández
 */
public class DataBaseAccesor {

    private String DB_CONNECTION;
    private String DB_HOST;
    private String DB_PORT;
    private String DB_DATABASE;
    private String DB_USERNAME;
    private String DB_PASSWORD;

    private DataBaseConnectionsPool connectionPool;
    FileChangeListener changeListener;
    private boolean hasValidSettings = true;

    public DataBaseAccesor( DataBaseConnectionsPool connectionPool ) {
        
        this.connectionPool = connectionPool;
        changeListener = new FileChangeListener(this);
        changeListener.start();
        

    }

    public Connection getConnection() throws DBQueryException, DBInvalidSettingsException, DBFileException {
        
        this.readConfigFile();

        Connection connection = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", DB_USERNAME);
        connectionProps.put("password", DB_PASSWORD);

        try {

            if (DB_CONNECTION.equals("mysql")) {

                String link = "jdbc:" + DB_CONNECTION + "://"
                        + DB_HOST
                        + ":" + DB_PORT + "/";

                connection = DriverManager.getConnection(link, connectionProps);

            } else if (DB_CONNECTION.equals("derby")) {

                connection = DriverManager.getConnection(
                        "jdbc:" + DB_CONNECTION + ":"
                        + DB_HOST
                        + ";create=true",
                        connectionProps);
                
            }
            
        } catch (SQLException ex) {
            
            throw new DBQueryException(ex);
            
        }
        return connection;

    }

    private void readConfigFile() throws DBFileException {
          
        JSONParser parser = new JSONParser();
        String filePath = new File("").getAbsolutePath();
        
        try {
            
            Object obj = parser.parse(new FileReader(filePath + "\\config\\database_config.json"));
            JSONObject jsonObject = (JSONObject) obj;

            DB_CONNECTION = (String) jsonObject.get("DB_CONNECTION");
            DB_HOST = (String) jsonObject.get("DB_HOST");
            DB_PORT = (String) jsonObject.get("DB_PORT");
            DB_DATABASE = (String) jsonObject.get("DB_DATABASE");
            DB_USERNAME = (String) jsonObject.get("DB_USERNAME");
            DB_PASSWORD = (String) jsonObject.get("DB_PASSWORD");

        } catch (FileNotFoundException e) {
            
            throw new DBFileException(e);
            
        } catch (IOException | ParseException  e) {
            
            throw new DBFileException(e);
            
        }

    }

    public void notifyFileChange() {
        
        if (isConnectionWorking()) {
            
            this.connectionPool.updateConnections();
            changeListener.run();
            
        } 
    }

    private boolean isConnectionWorking() {
        
        try {
            
           this.getConnection();
           
        } catch (Exception ex) {
            
            return false;
        }
        
        return true;
        
    }

}
