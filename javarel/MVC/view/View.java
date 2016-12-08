/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


/**
 *
 * @author RIKI
 */
public class View {
    private ViewConfiguration viewConfig;
    
    public View(){
        viewConfig = new ViewConfiguration();
    }
    
    public void callService(String serviceName, Event event){
        try{
            Service service = viewConfig.obtainService(serviceName);
            Controller control = (Controller) reflect(service.getController());
            Method method = control.getClass().getMethod(service.getMethod(), Event.class);
            method.invoke(control, event);
        } catch(Exception ex){
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Object reflect(String controller){  
        Object obj = null;
	try{
	        //load the AppTest at runtime
		Class cls = Class.forName(controller);
		obj = cls.newInstance();

	}catch(Exception ex){
		ex.printStackTrace();
	}
        
        return obj;
   }
    
}
