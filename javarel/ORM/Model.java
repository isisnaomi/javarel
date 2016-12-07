package javarel.ORM;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javarel.Pool.*;
import javarel.ORM.Exceptions.*;
import javarel.resources.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author IsisNaomi
 */
public class Model {
    private String tableName;
    private ArrayList<String> fieldsName = new ArrayList<String>();
    private ArrayList<Class> fieldsClass = new ArrayList<Class>();
    private ArrayList<Object> instances = new ArrayList<Object>();
    private ResultSet myRs = null;
    String className = this.getClass().getSimpleName().toLowerCase();
    //private DataBaseConn dbc = new DataBaseConn();//
    private DatabaseConnectionsPool dbcp;
    private boolean isXML = false;
    private XMLFileManager xmlManager = new XMLFileManager( className, "root", "class", "table" ); 

    public Model() throws Exception  {
        
        DatabaseConnectionsPool dbcp = new DatabaseConnectionsPool();
        
    }
    
    public void initialize() throws NonExistentMappingException {
        
        createModelInfo();
        checkForXML( );
        tableName = xmlManager.getTableName();
        
    }
    public ArrayList all() throws ErroneousRelationshipException, ErroneousObjectException, ErroneousMappingException {
        
                
        
        String methodName;
        String parameter;
        Class parameterClass;
        String query = "select * from " + tableName;
        
        try {
            
            //dbc.connect();
            DatabaseConnection connection = dbcp.acquireConnection();
            //myRs = dbc.excecuteQuery(query);
            myRs = connection.query(query);
            dbcp.releaseConnection( connection );
            
            
        } catch (Exception ex) {
            
            throw new ErroneousRelationshipException();
            
        }

        try {
            while (myRs.next()) {
                
                Object currentObject;
                
                try {
                    
                    currentObject = this.getClass().newInstance();
                    
                } catch (InstantiationException | IllegalAccessException ex) {
                    
                    throw new ErroneousObjectException();
                }
                for(int i=0; i<fieldsName.size(); i++){
                      
                    
                    String fieldName = fieldsName.get(i).substring(0, 1).toUpperCase() + fieldsName.get(i).substring(1);
                    methodName = "set" + fieldName;
                    parameter =myRs.getString(fieldsName.get(i));
                    parameterClass = fieldsClass.get(i);
                    Method methodSetProperty;
                    
                    try {
                        methodSetProperty = currentObject.getClass().getMethod(methodName, parameterClass );
                        
                    } catch (NoSuchMethodException | SecurityException ex) {
                        
                        throw new ErroneousObjectException();
                    }
                    
                    Object parameterconv = convertParameterTo(parameter, parameterClass);
                    
                    try {
                        methodSetProperty.invoke(currentObject, parameterconv);
                        
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                        
                        throw new ErroneousObjectException();
                    }
                    
                }
                instances.add(currentObject);
                
            }
        } catch (SQLException ex) {
            
            throw new ErroneousMappingException();
        }
        
       return instances; 
    }
    
    
    private void checkForXML( ) throws NonExistentMappingException {
        String filePath = new File("").getAbsolutePath();
        try{
            
            filePath = filePath.concat("\\config\\"+ className + "_config.xml");
            
            File f = new File(filePath);
            
            if(f.exists() && !f.isDirectory()) { 
                
                xmlManager.readXML();

            }
            else {

                    xmlManager.createXML();
            }
        } catch (Exception ex) {
            
                throw new NonExistentMappingException();
                
        }
    }
    
    private void createModelInfo(){ 
        
        tableName = className.concat("s");
        Field[] fields = this.getClass().getDeclaredFields();
        String fieldName;
        Class fieldClass;
        for (Field field : fields) {
            
            fieldName = field.getName();
            fieldClass = field.getType();
            this.fieldsName.add(fieldName);
            this.fieldsClass.add(fieldClass);
            
        }
        
    }
    private Object convertParameterTo(String parameter, Class parameterClass) {
        
         if( Boolean.class == parameterClass ) return Boolean.parseBoolean( parameter );
         if( Byte.class == parameterClass ) return Byte.parseByte( parameter );
         if( Short.class == parameterClass ) return Short.parseShort( parameter );
         if( Integer.TYPE == parameterClass ) return (Integer.parseInt( parameter ));
         if( Long.class == parameterClass ) return Long.parseLong( parameter );
         if( Float.class == parameterClass ) return Float.parseFloat( parameter );
         if( Double.class == parameterClass ) return Double.parseDouble( parameter );
         
    return parameter;
    }
}
