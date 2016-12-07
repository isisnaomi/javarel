
package javarel.Pool;

<<<<<<< HEAD
import javarel.DatabaseManager.DatabaseManager;


public class DatabaseConnectionsPool {

    private DatabaseManager databaseManager;
=======
import javarel.DB.DataBaseAccesor;

public class DatabaseConnectionsPool {

    private DataBaseAccesor databaseAccessor;
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b

    private int blockSize;

    private int maxPoolSize;

    private int amountBlocks;

    private int amountAcquiredConnections;

    private DatabaseConnection[] pool;


    public DatabaseConnectionsPool() {

        PoolConfigurationReader poolConfigurationReader = new PoolConfigurationReader();

<<<<<<< HEAD
        this.databaseManager = new DatabaseManager( this );
=======
        this.databaseAccessor = new DataBaseAccesor( this );
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b

        this.blockSize = poolConfigurationReader.getBlockSize();
        this.maxPoolSize = poolConfigurationReader.getMaxPoolSize();

        this.amountBlocks = 0;
        this.amountAcquiredConnections = 0;

        this.initializePool();

    }

    public DatabaseConnection acquireConnection() throws Exception {

        DatabaseConnection databaseConnection = this.searchForNotAcquiredConnection();

        databaseConnection.setAcquired( true );
        this.amountAcquiredConnections++;

        /* Check if increasePoolSizeIsNecessary */
        int totalConnections = this.blockSize * this.amountBlocks;
        int maxBlocksAmount = this.maxPoolSize / this.blockSize;

        boolean isPoolSizeIncreaseNecessary = totalConnections - this.amountAcquiredConnections > 1;
        boolean canIncreasePoolSize = this.amountBlocks < maxBlocksAmount;

        if ( isPoolSizeIncreaseNecessary && canIncreasePoolSize  ) {
            this.increasePoolSize();
        }
        /* End */

        return databaseConnection;

    }

    public void releaseConnection( DatabaseConnection connection ) throws Exception {

        if ( connection.isDeprecated() ) {
<<<<<<< HEAD
            connection.setConnection( this.databaseManager.getConnection() );
=======
            connection.setConnection( this.databaseAccessor.getConnection() );
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b
            connection.setDeprecated( false );
        }

        connection.setAcquired( false );
        this.amountAcquiredConnections--;

        /* checkIfShouldReducePoolSize */
        int totalConnections = this.blockSize * this.amountBlocks;
        boolean shouldReducePoolSize = ( totalConnections - this.amountAcquiredConnections ) > this.blockSize;

        if ( shouldReducePoolSize ) {
            this.decreasePoolSize();
        }
        /* End */

    }

<<<<<<< HEAD
    public void updateConnections() {
=======
    public void updateConnections() throws Exception {
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b

        for ( int i = 0; i < this.maxPoolSize; i++ ) {

            if ( this.pool[ i ] != null ) {
                DatabaseConnection actualIterationConnection = this.pool[i];
                if ( ! actualIterationConnection.isAcquired() ) {
<<<<<<< HEAD
                    actualIterationConnection.setConnection( this.databaseManager.getConnection() );
=======
                    actualIterationConnection.setConnection( this.databaseAccessor.getConnection() );
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b
                } else {
                    actualIterationConnection.setDeprecated( true );
                }
            }

        }

    }

    private DatabaseConnection searchForNotAcquiredConnection() throws Exception {

        int i = 0;
        int poolSize = this.pool.length;

        while ( true ) {

            if ( i == poolSize ) {

                throw new Exception("No connections available.");

            } else {

                DatabaseConnection actualIterationConnection;
                actualIterationConnection = this.pool[ i ];

                if ( actualIterationConnection.isAcquired() ) {
                    i++;
                    continue;
                } else {
                    return actualIterationConnection;
                }

            }

        }

    }

    private void increasePoolSize() throws Exception {

        int maxBlocksAmount = this.maxPoolSize / this.blockSize;
        boolean canIncreasePoolSize = this.amountBlocks < maxBlocksAmount;

        if ( canIncreasePoolSize ) {

            int indexFirstNotInitializedConnection = ( this.blockSize * this.amountBlocks );

            for ( int i = 0; i < this.blockSize; i++ ) {
                this.pool[ indexFirstNotInitializedConnection ] =
<<<<<<< HEAD
                        new DatabaseConnection( indexFirstNotInitializedConnection, this.databaseManager.getConnection() );
=======
                        new DatabaseConnection( indexFirstNotInitializedConnection, this.databaseAccessor.getConnection() );
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b
                indexFirstNotInitializedConnection++;
            }

            this.amountBlocks++;

        } else {
            throw new Exception("Pool is already at its maximum size.");
        }

    }

    private void decreasePoolSize() throws Exception {

        int totalConnections = this.blockSize * this.amountBlocks;
        boolean canReducePoolSize =
                ( totalConnections - this.amountAcquiredConnections ) > this.blockSize;

        if ( canReducePoolSize ) {

            DatabaseConnection[] newPool = new DatabaseConnection[ this.maxPoolSize ];
            int firstNotInitializedPoolIndex = 0;

            for ( int i = 0; i < this.maxPoolSize; i++ ) {
                if ( this.pool[ i ] != null ) {
                    DatabaseConnection actualIterationConnection = this.pool[ i ];
                    actualIterationConnection.setId( firstNotInitializedPoolIndex );
                    newPool[ firstNotInitializedPoolIndex ] = actualIterationConnection;
                    firstNotInitializedPoolIndex++;
                }
            }

            if ( ( firstNotInitializedPoolIndex % this.blockSize ) != 0 ) {
                int stopAt = this.blockSize - (firstNotInitializedPoolIndex % this.blockSize);

                for ( int i = 0; i < stopAt; i++ ) {
                    newPool[ firstNotInitializedPoolIndex ] =
<<<<<<< HEAD
                            new DatabaseConnection( firstNotInitializedPoolIndex, this.databaseManager.getConnection() );
=======
                            new DatabaseConnection( firstNotInitializedPoolIndex, this.databaseAccessor.getConnection() );
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b
                }
            }

            this.pool = newPool;
            this.amountBlocks--;

        } else {
            throw new Exception( "Cannot reduce poolSize" );
        }

    }

    private void initializePool() {

        this.pool = new DatabaseConnection[ this.maxPoolSize ];
        for ( int i = 0; i < this.blockSize; i++ ) {
<<<<<<< HEAD
            this.pool[ i ] = new DatabaseConnection( i, this.databaseManager.getConnection() );
=======
            this.pool[ i ] = new DatabaseConnection( i, this.databaseAccessor.getConnection() );
>>>>>>> 553ad725d1dc7c017dfed4aaebb1ec4b3de9764b
        }

        this.amountBlocks++;

    }

}