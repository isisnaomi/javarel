
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mvc_exceptions.CallServiceNotFoundException;
import mvc_exceptions.IncorrectAttributeNameException;
import mvc_exceptions.IncorrectAttributeValueException;
import mvc_exceptions.IncorrectAttributesNumberException;
import mvc_exceptions.InexistentCallServicesException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author RIKI
 */
public class ViewConfiguration {
    private final String configFilePath;
    
    public ViewConfiguration(){
        configFilePath = generateConfigFilePath();
    }
    
    public String getConfigFilePath(){
        return configFilePath;
    }
    
    public Service obtainService(String serviceName) throws Exception{
        Service service = null;
        
            Document configFile = obtainConfigFile();
            NodeList callServicesList = configFile.getElementsByTagName("callservice");
            int numCallServices = callServicesList.getLength();
            evaluateExistenceOfCS(numCallServices);
            for(int count = 0; count < numCallServices; count++){
                Element callService = (Element) callServicesList.item(count);
                evaluateCSAttributes(callService);
                if(callService.getAttribute("name").equals(serviceName)) {
                    String strController = callService.getAttribute("controller");
                    String strMethod = callService.getAttribute("method");
                    service = new Service(strController, strMethod);
                    break;
                }
            }
            
            evaluateCSFound(service);
            
        return service;
    }
    
    private String generateConfigFilePath(){
        String absolutePath = new File("").getAbsolutePath();
        String configPath = absolutePath + "/config/mvc_config.xml";
        
        return configPath;
    }
    
    private Document obtainConfigFile() throws ParserConfigurationException, SAXException, IOException{
        Document configFile = null;
        DocumentBuilderFactory buildFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = buildFact.newDocumentBuilder();
        String dir = getConfigFilePath();
        configFile = docBuilder.parse(dir);
        
        return configFile;
    }
    
    private void evaluateCSAttributes(Element callService) throws IncorrectAttributesNumberException, IncorrectAttributeNameException, IncorrectAttributeValueException{
        int numAttributes = callService.getAttributes().getLength();
        if(numAttributes != 3){
            throw new IncorrectAttributesNumberException();
        }
        NamedNodeMap attributesList = callService.getAttributes();
        for(int index = 0; index < numAttributes; index++){
            String attrName = attributesList.item(index).getNodeName();
            System.out.println(attrName);
            if(!attrName.equals("name") && !attrName.equals("controller") &&
               !attrName.equals("method")){
                throw new IncorrectAttributeNameException(attrName);
            }
            String attrValue = attributesList.item(index).getNodeValue();
            if(attrValue.equals("")){
                throw new IncorrectAttributeValueException(attrName);
            }
        }
    }
    
    private void evaluateExistenceOfCS(int numCallServices) throws InexistentCallServicesException{
        if(numCallServices == 0){
            throw new InexistentCallServicesException();
        }
    }
    
    private void evaluateCSFound(Service service) throws CallServiceNotFoundException{
        if(service == null){
            throw new CallServiceNotFoundException();
        }
    }
    
}

