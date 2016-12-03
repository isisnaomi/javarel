package com.logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by shado on 02/12/2016.
 */
public class LogConfigurationReader {

    public String[] readLevelsConfiguration() throws FileNotFoundException, IOException {
        String[] levels = null;

        String genericString;
        String levelConfig = "";
        FileReader f = new FileReader( "configurationLog.txt" );
        BufferedReader b = new BufferedReader( f );
        int configurationLine = 1;
        int i = 1;

        while ( ( genericString = b.readLine() ) != null ) {
            if( configurationLine == i ) {
                levelConfig = genericString;
            }
            i++;
        }

        levels = levelConfig.split("-");

        return levels;
    }

    public int readConfigFileSize() throws FileNotFoundException, IOException{
        int fileSize = -1;

        String genericString;
        int sizeConfig = -1;
        FileReader f = new FileReader( "configurationLog.txt" );
        BufferedReader b = new BufferedReader( f );
        int configurationLine = 2;
        int i = 1;

        while (  ( genericString = b.readLine() ) != null ) {
            if (configurationLine == i) {
                sizeConfig = Integer.parseInt(genericString);
            }
            i++;
        }

        fileSize = sizeConfig;
        return fileSize;
    }

}
