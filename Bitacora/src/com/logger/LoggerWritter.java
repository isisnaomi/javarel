package com.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by shado on 02/12/2016.
 */
public class LoggerWritter {

    public void writeLog( String log, int fileSize ) {

        String ruta = "log.txt";
        File file = new File( ruta );
        BufferedWriter bw;

        try {
            bw = new BufferedWriter(new FileWriter( file, true ));
            long currentSize = file.length();

            if(fileSize > currentSize) {
                bw.write( log + "\r\n");
                bw.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
