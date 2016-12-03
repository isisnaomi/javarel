import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

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
    private DataBaseConn dbc = new DataBaseConn();
    private boolean isXML = false;

    public Model()  {
        
        
        
    }
    public void initialize()throws Exception{
        createModelInfo();
        checkForXML();
    }
    

    public ArrayList all() throws Exception{
        
        String query = "select * from " + tableName;
        dbc.connect();
        myRs = dbc.excecuteQuery(query);
        String methodName;
        String parameter;
        Class parameterClass;
       

        while (myRs.next()) {

            Object currentObject = this.getClass().newInstance();
            for(int i=0; i<fieldsName.size(); i++){
                
                
                String fieldName = fieldsName.get(i).substring(0, 1).toUpperCase() + fieldsName.get(i).substring(1); 
                methodName = "set" + fieldName;
                parameter =myRs.getString(fieldsName.get(i));
                parameterClass = fieldsClass.get(i);
                Method methodSetProperty = currentObject.getClass().getMethod(methodName, parameterClass );
                Object parameterconv = convertParameterTo(parameter, parameterClass);
                methodSetProperty.invoke(currentObject, parameterconv);  
                
            }
            instances.add(currentObject);
            
	}
       return instances; 
    }
    
    private void createModelInfo(){ 
        tableName = className.concat("s");
        Field[] fields = this.getClass().getDeclaredFields();
        String fieldName;
        Class fieldClass;
        for(int i=0; i<fields.length ; i++){
            fieldName = fields[i].getName();
            fieldClass = fields[i].getType();
            this.fieldsName.add(fieldName);
            this.fieldsClass.add(fieldClass);
        }
    }
    private void checkForXML() throws Exception {
        File f = new File(className + "_config.xml");
        if(f.exists() && !f.isDirectory()) { 
            readXML();
            
        }
        else {
            createXML();
        }
    }
    private void readXML(){
        try {

	File fXmlFile = new File(className + "_config.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

        
        Node node = doc.getDocumentElement();
        Element element = (Element) node;
        tableName = element.getElementsByTagName("table").item(0).getTextContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createXML()throws TransformerException, SAXException, ParserConfigurationException, IOException {
        if (!isXML){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

            Element root = document.createElement("mapping");
            document.appendChild(root);

            Element className = document.createElement("class");
            className.appendChild(document.createTextNode(this.getClass().getSimpleName()));
            root.appendChild(className);

            Element tableName = document.createElement("table");
            tableName.appendChild(document.createTextNode(this.tableName));
            root.appendChild(tableName);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        StreamResult result = new StreamResult(this.className +"_config.xml");
        transformer.transform(source, result);
        isXML = true;
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
