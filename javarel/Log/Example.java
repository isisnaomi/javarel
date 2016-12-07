package javarel.Log;

import javarel.Log.exceptions.ClassNotFoundException;

/**
 * Created by shado on 06/12/2016.
 */
public class Example {

    public static void main(String[] args) {
        LoggerMessenger messenger = new Logger();
        try {
            messenger.encode( Example.class, 0, "Mensaje de prueba" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
