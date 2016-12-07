package javarel.resources;


import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
public class XMLFileManager {
    String filePath = new File("").getAbsolutePath();
    String fileName;
    private boolean isXML = false;
    String tableName;
    String rootTag;
    String classNameTag;
    String tableNameTag;

    public String getTableName() {
        
        return tableName;
        
    }

    public XMLFileManager(String fileName) {
        
        this.fileName = fileName;
        
    }
     public XMLFileManager(String fileName,String rootTag, String classNameTag, String tableNameTag ) {
         
        this.fileName = fileName;
        
    }
    

    public void readXML() throws SAXException, IOException, ParserConfigurationException{
	File fXmlFile = new File(filePath +"\\src\\config\\" + fileName + "_config.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);

        
        Node node = doc.getDocumentElement();
        Element element = (Element) node;
        this.tableName = element.getElementsByTagName("table").item(0).getTextContent();
       
    }
    public void createXML(  )throws TransformerException, SAXException, ParserConfigurationException, IOException {
        if (!isXML){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

            Element root = document.createElement(rootTag);
            document.appendChild(root);

            Element className = document.createElement(classNameTag);
            className.appendChild(document.createTextNode(this.getClass().getSimpleName()));
            root.appendChild(className);

            Element tableName = document.createElement(tableNameTag);
            tableName.appendChild(document.createTextNode(this.tableName));
            root.appendChild(tableName);

        DOMSource source = new DOMSource(document);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
            System.out.println(filePath+"\\src\\config\\" + this.fileName +"_config.xml");
        StreamResult result = new StreamResult(filePath+"\\src\\config\\" + this.fileName +"_config.xml");
        transformer.transform(source, result);
        
        isXML = true;
        
        }
        
    }

    
}
