package javarel.Log;

import javarel.Log.exceptions.ConfigurationFileNotFoundException;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/**
 * Created by shado on 06/12/2016.
 */
public class LogConfigurationReader {

    public String[] readLevelsConfiguration() throws FileNotFoundException, ConfigurationFileNotFoundException {
        String[] levels = null;

        String genericString;
        String levelConfig = "";
        String filePath = new File("").getAbsolutePath();
        FileReader f = new FileReader( filePath + "/config/log_config.txt" );
        BufferedReader b = new BufferedReader( f );
        int configurationLine = 1;
        int i = 1;

        try {
            while ( ( genericString = b.readLine() ) != null ) {
                if( configurationLine == i ) {
                    levelConfig = genericString;
                }
                i++;
            }
        } catch (IOException e) {
            throw new ConfigurationFileNotFoundException();
        }

        levels = levelConfig.split("-");

        return levels;
    }

    public int readConfigFileSize() throws FileNotFoundException, ConfigurationFileNotFoundException {
        int fileSize = -1;

        String genericString;
        int sizeConfig = -1;
        String filePath = new File("").getAbsolutePath();
        FileReader f = new FileReader( filePath + "/config/log_config.txt" );
        BufferedReader b = new BufferedReader( f );
        int configurationLine = 2;
        int i = 1;

        try {
            while (  ( genericString = b.readLine() ) != null ) {
                if (configurationLine == i) {
                    sizeConfig = Integer.parseInt(genericString);
                }
                i++;
            }
        } catch (IOException e) {
            throw new ConfigurationFileNotFoundException();
        }

        fileSize = sizeConfig;
        return fileSize;
    }

}
