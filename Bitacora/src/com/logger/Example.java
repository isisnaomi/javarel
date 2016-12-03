package com.logger;

/**
 * Created by shado on 02/12/2016.
 */
public class Example {

    public static void main(String[] args) {
        LoggerMessenger messenger = new Logger();
        messenger.encode( Example.class, 0, "Mensaje de prueba" );
    }
}
