package com.logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shado on 02/12/2016.
 */
public class Logger implements LoggerMessenger {

    private String[] levels;
    private int fileSize;
    private LogConfigurationReader reader;
    private LoggerWritter writter;

    public Logger() {
        this.writter = new LoggerWritter();
        this.reader = new LogConfigurationReader();

        try {
            this.levels = this.reader.readLevelsConfiguration();
            this.fileSize = this.reader.readConfigFileSize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat( "dd/MM/yyyy-HH:mm:ss" );

        return dateFormat.format( date );
    }

    private String getLevel( int levelNumb ) {
        String level = this.levels[ levelNumb ];
        level = " [" + level + "] ";
        return level;
    }

    private void printLog( String log ) {
        System.out.println( log );
        this.writter.writeLog( log, this.fileSize );
    }

    @Override
    public void encode( Class clss, int level, String msg ) {
        String date = getDate();
        String levelString = getLevel( level );

        String log = date + levelString + clss.getCanonicalName() + " - " + msg;
        printLog( log );

    }
}
