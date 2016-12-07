
package javarel.Pool.Exceptions;


public class PoolNoConnectionsAvailableException extends Exception {

    public PoolNoConnectionsAvailableException( String message ) {

        super( message );

    }

    public PoolNoConnectionsAvailableException( String message, Throwable throwable ) {

        super( message, throwable );

    }

}
