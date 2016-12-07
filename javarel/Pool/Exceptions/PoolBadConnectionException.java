
package javarel.Pool.Exceptions;


public class PoolBadConnectionException extends Exception {

    public PoolBadConnectionException( String message ) {

        super( message );

    }

    public PoolBadConnectionException( String message, Throwable throwable ) {

        super( message, throwable );

    }

}
