
package javarel.Pool.Exceptions;


public class PoolBadConfigurationException extends Exception {

    public PoolBadConfigurationException( String message ) {

        super( message );

    }

    public PoolBadConfigurationException( String message, Throwable throwable ) {

        super( message, throwable );

    }

}
