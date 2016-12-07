/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author RIKI
 */
public class Service {
    private String controller;
    private String method;
    
    public Service(String controller, String method){
        this.controller = controller;
        this.method = method;
    }
    
    public String getController(){
        return controller;
    }
    
    public String getMethod(){
        return method;
    }
}
