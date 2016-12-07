
package javarel.Pool;


import javarel.DB.exceptions.DBQueryException;
import javarel.DB.utils.DBConnection;

import java.sql.ResultSet;


public class DatabaseConnection {

    private int id;

    private DBConnection connection;

    private boolean acquired;

    private boolean deprecated;


    DatabaseConnection( int id, DBConnection connection ) {
        this.id = id;
        this.connection = connection;
    }

    public void testPrintId() {
        System.out.println( this.getId() );
    }

    public ResultSet query( String query ) throws DBQueryException {

        ResultSet resultSet = this.connection.query( query );

        return resultSet;
    }

    int getId() {
        return id;
    }

    void setId( int id ) {
        this.id = id;
    }


    protected DBConnection getConnection() {
        return connection;
    }

    void setConnection(DBConnection connection) {

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
    
    void disconnect(){
        this.connection.disconnect();
    }
}