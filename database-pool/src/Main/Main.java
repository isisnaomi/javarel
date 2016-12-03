package Main;

import javarel.DatabaseConnectionsPool.DatabaseConnectionsPool;
import javarel.DatabaseConnectionsPool.DatabaseConnection;


public class Main {

    public static void main( String[] args ) {

        DatabaseConnectionsPool databaseConnectionsPool = new DatabaseConnectionsPool();

        try {

            DatabaseConnection[] databaseConnections = new DatabaseConnection[ 3245 ];

            for ( int i = 0; i < 25; i++ ) {

                databaseConnections[ i ] = databaseConnectionsPool.acquireConnection();
                databaseConnections[ i ].testPrintId();

            }

            for ( int i = 24; i >= 0; i-- ) {

                databaseConnections[ i ].testPrintId();
                databaseConnectionsPool.releaseConnection( databaseConnections[ i ] );

            }

        } catch( Exception e ) {

            e.printStackTrace();

        }

    }
}
