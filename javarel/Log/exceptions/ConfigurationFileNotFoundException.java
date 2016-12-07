package javarel.Log.exceptions;

/**
 * Created by shado on 06/12/2016.
 */
public class ConfigurationFileNotFoundException extends Exception {
    public ConfigurationFileNotFoundException(){};

    public void shwoMessage(){
        System.out.println("Configuration File Not Found.");
    }
}
