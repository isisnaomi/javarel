
package javarel.Pool;


import javarel.DB.DataBaseAccessor;


public class DatabaseConnectionsPool {

    private DataBaseAccessor databaseAccessor;


    private int blockSize;

    private int maxPoolSize;

    private int amountBlocks;

    private int amountAcquiredConnections;

    private DatabaseConnection[] pool;


    public DatabaseConnectionsPool() throws Exception {

        PoolConfigurationReader poolConfigurationReader = new PoolConfigurationReader();


        this.databaseAccessor = new DataBaseAccessor( this );


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

            connection.setConnection( this.databaseAccessor.getConnection() );

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


    public void updateConnections() {


        for ( int i = 0; i < this.maxPoolSize; i++ ) {

            if ( this.pool[ i ] != null ) {
                DatabaseConnection actualIterationConnection = this.pool[i];
                if ( ! actualIterationConnection.isAcquired() ) {

                    try {
                        actualIterationConnection.setConnection( this.databaseAccessor.getConnection() );
                    } catch ( Exception e ) {
                        e.printStackTrace();
                    }

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

                throw new Exception( "No connections available." );

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

                        new DatabaseConnection( indexFirstNotInitializedConnection, this.databaseAccessor.getConnection() );

                indexFirstNotInitializedConnection++;
            }

            this.amountBlocks++;

        } else {
            throw new Exception( "Pool is already at its maximum size." );
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
                int stopAt = this.blockSize - ( firstNotInitializedPoolIndex % this.blockSize );

                for ( int i = 0; i < stopAt; i++ ) {
                    newPool[ firstNotInitializedPoolIndex ] =

                            new DatabaseConnection( firstNotInitializedPoolIndex, this.databaseAccessor.getConnection() );

                }
            }

            this.pool = newPool;
            this.amountBlocks--;

        } else {
            throw new Exception( "Cannot reduce poolSize" );
        }

    }

    private void initializePool() throws Exception {

        this.pool = new DatabaseConnection[ this.maxPoolSize ];
        for ( int i = 0; i < this.blockSize; i++ ) {

            this.pool[ i ] = new DatabaseConnection( i, this.databaseAccessor.getConnection() );

        }

        this.amountBlocks++;

    }

}