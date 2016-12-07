
package javarel.Pool;

import java.sql.*;

public class DatabaseConnection {

    private int id;

    private Connection connection;

    private boolean acquired;

    private boolean deprecated;


    DatabaseConnection( int id, Connection connection ) {
        this.id = id;
        this.connection = connection;
    }

    public void testPrintId() {
        System.out.println( this.getId() );
    }

    public ResultSet query( String query ) throws SQLException {

        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery( query );

        return resultSet;
    }

    int getId() {
        return id;
    }

    void setId( int id ) {
        this.id = id;
    }

<<<<<<< HEAD
    Connection getConnection() {
        return connection;
    }

    void setConnection( Connection connection ) {
=======
    protected Connection getConnection() {
        return connection;
    }

    void setConnection(Connection connection) {
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b
        this.connection = connection;
    }

    boolean isAcquired() {
        return acquired;
    }

    void setAcquired( boolean acquired ) {
        this.acquired = acquired;
    }

    boolean isDeprecated() {
        return deprecated;
    }

    void setDeprecated( boolean deprecated ) {
        this.deprecated = deprecated;
    }
}