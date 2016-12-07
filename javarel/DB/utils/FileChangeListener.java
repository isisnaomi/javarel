/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javarel.DB.utils;

import javarel.DB.DataBaseAccessor;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author Tony
 */
public class FileChangeListener extends Thread{
   private DataBaseAccessor DB;

    public FileChangeListener(DataBaseAccessor db) {
        this.DB = db;
    }
    
    @Override
    public void run() {
        System.out.println("filechangelistener corriendo");
        String firstContent = getFileContent();
        
        while(true){
            String actualContent = getFileContent();
            if( firstContent.equals(actualContent)== false ){
                System.out.println("detectado un cambio en el configfile");
                DB.notifyFileChange();
                break;
            }
        }
  
    }

    private String getFileContent() {
        char [] buffer = new char[200];
        try {
            String filePath = new File("").getAbsolutePath();
            File file = new File(filePath + "\\config\\database_config.json");
            FileReader reader = new FileReader(file);
            
            reader.read(buffer); 
            reader.close();

        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
           
       }
        String contain = new String(buffer);
        return contain;
    }
}
