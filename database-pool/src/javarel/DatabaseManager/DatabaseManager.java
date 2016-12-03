
package javarel.DatabaseManager;

import java.sql.*;

import javarel.DatabaseConnectionsPool.DatabaseConnectionsPool;


public class DatabaseManager {

    // JDBC driver name and database URL
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:8889/astrosalsa";

    //  Database credentials
    private static final String USER = "root";
    private static final String PASS = "root";

    private DatabaseConnectionsPool databaseConnectionsPool;

    public DatabaseManager( DatabaseConnectionsPool databaseConnectionsPool ) {

        this.databaseConnectionsPool = databaseConnectionsPool;

    }

    public Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName( "com.mysql.jdbc.Driver" );

            connection = DriverManager.getConnection( DB_URL, USER, PASS );

        } catch ( Exception e ) {

            e.printStackTrace();

        }

        return connection;

    }

}

